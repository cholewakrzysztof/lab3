package pwr.student.FrontEnd;

import pwr.student.BackEnd.BackGate;
import pwr.student.BackEnd.Respond;

public class Main {
    public static void main(String[] args) throws Exception {
        FrontGate frontGate = new FrontGate();
        BackGate backGate = new BackGate("decision");

        try {
            while(true){
                frontGate.sendRequest(backGate,RequestBuilder.buildRequest());
                Respond respond = frontGate.getRespond(backGate);
                switch (respond.getOperation()) {
                    case SELECT,SEARCH_SELECT -> DatabaseResultPrinter.printTableResult(respond.getResult(), respond.getColumns());
                    case DELETE -> System.out.println("Successfully deleted");
                    case INSERT -> System.out.println("Successfully inserted");
                    case ERROR -> System.out.println("Something gone wrong");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
