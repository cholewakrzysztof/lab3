package pwr.student.FrontEnd;

import pwr.student.BackEnd.Operation;
import pwr.student.BackEnd.Request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.exit;

public class RequestBuilder {
    public static Request buildRequest() throws Exception {
        Request req = new Request();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose operation type:");
        System.out.println("SELECT............s");
        System.out.println("SEARCH SELECT....ss");
        System.out.println("DELETE............d");
        System.out.println("INSERT............i");
        System.out.println("QUIT..............q");
        switch (scanner.nextLine()){
            case "s" -> req.setOperation(Operation.SELECT);
            case "ss" -> req.setOperation(Operation.SEARCH_SELECT);
            case "d" -> req.setOperation(Operation.DELETE);
            case "i" -> req.setOperation(Operation.INSERT);
            case "q" -> {System.out.println("Exit");exit(0);}
            default -> throw new Exception("Wrong option typed");
        }

        boolean restart = true;
        while (restart) {
            switch (req.getOperation()) {
                case SELECT -> {
                    System.out.println("Type columns with space between names");
                    String[] columns = scanner.nextLine().split(" ");
                    if (DataValidator.validColumns(columns)) {
                        restart = false;
                        req.setColumns(columns);
                    }else
                        System.out.println("Wrong columns abort mission");

                }
                case DELETE -> {
                    System.out.println("Type scope of indexes to delete 'start,end'");
                    HashMap<String, String> params = buildParams(Operation.DELETE);
                    if (DataValidator.validScope(params)) {
                        restart = false;
                        req.setParams(params);
                    }else
                        System.out.println("Wrong typed scope");
                }
                case INSERT -> {
                    System.out.println("Start of building insert, type enter to chose each default option");
                    HashMap<String, String> params = buildParams(Operation.INSERT);

                    if (DataValidator.validInsertParams(params).equals("true")) {
                        restart = false;
                        req.setParams(params);
                    }else {
                        System.out.println(DataValidator.validInsertParams(params));
                    }
                }
                case SEARCH_SELECT -> {

                    while (restart) {
                        System.out.println("Start of building searching params with ' ' between them (column value), type enter to end");
                        HashMap<String, String> params = buildParams(Operation.SEARCH_SELECT);

                        if (DataValidator.validSearchParams(params)) {
                            restart = false;
                            req.setParams(DataValidator.rebuildParams(params));
                            System.out.println("Type columns with space between names");
                            String[] columns = scanner.nextLine().split(" ");
                            if (DataValidator.validColumns(columns)) {
                                req.setColumns(columns);
                            } else {
                                restart = true;
                                System.out.println("Wrong columns abort mission");
                            }
                        } else {
                            System.out.println("Wrong params");
                        }
                    }
                }
                default -> throw new Exception("Not implemented");
            }
        }
        return req;
    }

    private static HashMap<String,String> buildParams(Operation op) throws Exception {
        HashMap<String,String> params = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        switch (op){
            case INSERT -> {
                String value;

                System.out.println("Type person name (default: Krzysztof)");
                value = scanner.nextLine();
                value = !value.isEmpty() ? value : "Krzysztof";
                params.put("person", value);

                System.out.println("Type date in format yyyy-MM-dd HH:mm:ss (default: today date)");
                value = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = !value.isEmpty() ? value : sdf.format(new Date());
                params.put("date", value);

                System.out.println("Type component name (default: default_component)");
                value = scanner.nextLine();
                value = !value.isEmpty() ? value : "default_component";
                params.put("component", value);

                System.out.println("Type priority (default: 0)");
                value = scanner.nextLine();
                value = !value.isEmpty() ? value : "0";
                params.put("priority", value);

                System.out.println("Type description (default: blank)");
                value = scanner.nextLine();
                value = !value.isEmpty() ? value : "blank";
                params.put("description", value);
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
