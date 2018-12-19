package proj.redmart.integrationTests;


import org.junit.Assert;
import org.junit.Test;
import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.visitor.Output;
import proj.redmart.models.spreadsheet.visitor.RowColumnLayoutVisitor;

import java.util.Iterator;
import java.util.List;

public class LayoutVistitorTest {

    @Test
    public void postFixEvaluataionTest(){
        RowColumnLayout.CellBuilder cellBuilder =RowColumnLayout.Builder.getBuilder().buildContainer(5,5)
                .addCell(1,2,"20")
                .addCell(2,1,"40")
                .addCell(1,1,"A2")
                .addCell(2,2,"A2")
                .addCell(2, 3,"A1 A2 * C3 +")
                .addCell(3,3,"0 B2 B1 *  -")
                .addCell(4,4,"C3 2 *");
        RowColumnLayout layout=cellBuilder.build();
        RowColumnLayoutVisitor visitor = new RowColumnLayoutVisitor();
        Output result=visitor.visit(layout);

        List<Output.Message> messages=result.getMessages();



        Output expected=new Output.Builder()
                .addStandardMessage("20.00000")
                .addStandardMessage("20.00000")
                .addStandardMessage("40.00000")
                .addStandardMessage("20.00000")
                .addStandardMessage("-400.00000")
                .addStandardMessage("-800.00000")
                .addStandardMessage("-1600.00000")
        .build();
        ;

        Assert.assertEquals("value for A1",result.getMessages().get(0).getData(),"20.00000");
        Assert.assertEquals("value for A2",result.getMessages().get(1).getData(),"20.00000");
        Assert.assertEquals("value for B1",result.getMessages().get(2).getData(),"40.00000");
        Assert.assertEquals("value for B2",result.getMessages().get(3).getData(),"20.00000");
        Assert.assertEquals("value for B3",result.getMessages().get(4).getData(),"-400.00000");
        Assert.assertEquals("value for C3",result.getMessages().get(5).getData(),"-800.00000");
        Assert.assertEquals("value for D4",result.getMessages().get(6).getData(),"-1600.00000");
    }


    @Test
    public void cyclicDependencyTest(){
        RowColumnLayout.CellBuilder cellBuilder =RowColumnLayout.Builder.getBuilder().buildContainer(5,5)
                .addCell(1,1,"A2")
                .addCell(1,2,"A3")
                .addCell(1,3, "A1");
        RowColumnLayout layout=cellBuilder.build();
        Output op=layout.accept(new RowColumnLayoutVisitor());
        Assert.assertEquals("cyclic dependency on cell","cyclic dependency on  cell  = A1 data=A2",op.getMessages().get(0).getData());
        Assert.assertEquals("cyclic dependency on cell","cyclic dependency on  cell  = A2 data=A3",op.getMessages().get(1).getData());
        Assert.assertEquals("cyclic dependency on cell","cyclic dependency on  cell  = A3 data=A1",op.getMessages().get(2).getData());
        Assert.assertTrue(op.hasException());
    }



}
