package pwr.student.FrontEnd;

import pwr.student.BackEnd.Operation;
import pwr.student.BackEnd.Request;

import java.util.Scanner;

public class RequestBuilder {
    public static Request buildRequest() throws Exception {
        Request req = new Request();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose operation type:");
        System.out.println("SELECT............s");
        System.out.println("SEARCH SELECT....ss");
        System.out.println("DELETE............d");
        System.out.println("INSERT............i");

        switch (scanner.nextLine()){
            case "s" -> req.setOperation(Operation.SELECT);
            case "ss" -> req.setOperation(Operation.SEARCH_SELECT);
            case "d" -> req.setOperation(Operation.DELETE);
            case "i" -> req.setOperation(Operation.INSERT);
            default -> throw new Exception("Wrong option typed");
        }

        switch (req.getOperation()){
            case SELECT -> {
                System.out.println("Type columns with space between names");
                String[] columns = scanner.nextLine().split(" ");
                if(DataValidator.validColumns(columns))
                    req.setColumns(columns);
                else
                    System.out.println("Wrong columns abort mission");

            }
            case DELETE, INSERT, SEARCH_SELECT -> throw new Exception("Not implemented");
        }

        return req;
    }
}
