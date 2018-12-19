package proj.redmart.execute;

import proj.redmart.models.spreadsheet.Cell;
import proj.redmart.models.spreadsheet.RowColumnLayout;
import proj.redmart.models.spreadsheet.cellutils.CyclicValidationException;
import proj.redmart.models.spreadsheet.cellutils.InvalidCellReferenceException;
import proj.redmart.models.spreadsheet.visitor.Output;
import proj.redmart.models.spreadsheet.visitor.Report;
import proj.redmart.models.spreadsheet.visitor.RowColumnLayoutVisitor;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {






        RowColumnLayout layout1 = RowColumnLayout.Builder
                .getBuilder().buildContainer(10,10)
                .addCell(1, 1, "A1")
                .addCell(1, 6, "10")
                .addCell(2, 3,"2")
                .addCell(2,4,"A2")
                .addCell(1,3,"A2")
                .addCell(5,3,"A3")
                .addCell(1,2,"A5")
                .addCell(1,5,"B4")
                .addCell(5,2,"B3 A6 + A6 *")
                .build();

        Scanner scanner = new Scanner(System.in);
        int columns=scanner.nextInt();
        int rows=scanner.nextInt();
        scanner.nextLine();
        RowColumnLayout.CellBuilder cellBuilder =RowColumnLayout.Builder.getBuilder().buildContainer(rows,columns);
        for (int i=1;i<=rows;i++){
            for (int j=1;j<=columns;j++){
                cellBuilder.addCell(i,j,scanner.nextLine().trim());
            }
        }
        RowColumnLayout layout=cellBuilder.build();
        RowColumnLayoutVisitor visitor =new RowColumnLayoutVisitor();
        Output output=layout.accept(visitor);
        for (Output.Message message:output.getMessages()){
            System.out.println(message.getData());
        }

        if(output.hasException()){
            System.exit(1);
        }

    }
}
