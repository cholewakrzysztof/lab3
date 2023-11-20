package pwr.student.FrontEnd;

import pwr.student.BackEnd.Operation;
import pwr.student.BackEnd.Request;

import java.util.HashMap;
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
            case DELETE ->{
                System.out.println("Type scope of indexes to delete 'start,end'");
                HashMap<String,String> params = buildParams(Operation.DELETE);
                if(DataValidator.validScope(params))
                    req.setParams(params);
                else
                    System.out.println("Wrong typed scope");
            }
            case INSERT -> {
                System.out.println("Start of building insert, type enter to chose each default option");
                HashMap<String,String> params = buildParams(Operation.INSERT);

                if(DataValidator.validInsertParams(params))
                    req.setParams(params);
                else
                    System.out.println("Wrong params");
            }
            case SEARCH_SELECT -> {
                System.out.println("Start of building searching params with ' ' between them, type enter to end");
                HashMap<String,String> params = buildParams(Operation.SEARCH_SELECT);

                if(DataValidator.validSearchParams(params))
                    req.setParams(params);
                else
                    System.out.println("Wrong params");
            }
            default -> throw new Exception("Not implemented");
        }

        return req;
    }

    private static HashMap<String,String> buildParams(Operation op) throws Exception {
        HashMap<String,String> params = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        switch (op){
            case INSERT -> {
                System.out.println("Type person name (default: Krzysztof)");
                params.put("person", scanner.nextLine());
                System.out.println("Type date (default: today date)");
                params.put("date", scanner.nextLine());
                System.out.println("Type component name (default: default_component)");
                params.put("component", scanner.nextLine());
                System.out.println("Type priority (default: 0)");
                params.put("priority", scanner.nextLine());
                System.out.println("Type description (default: blank)");
                params.put("description", scanner.nextLine());
            }
            case SEARCH_SELECT -> {
                while(true){
                    String[] input = scanner.nextLine().split(" ");
                    if(input.length>1) {
                        params.put(input[0], input[1]);
                        System.out.println("Type next condition:");
                    }else
                        break;
                }
            }
            case DELETE -> {
                String[] input = scanner.nextLine().split(",");
                params.put("start",input[0]);
                params.put("end",input[1]);
            }
            default -> throw new Exception("Not implemented");
        }
        return params;
    }
}
