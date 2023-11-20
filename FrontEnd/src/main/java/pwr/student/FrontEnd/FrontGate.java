package pwr.student.FrontEnd;

import pwr.student.BackEnd.BackGate;
import pwr.student.BackEnd.Request;
import pwr.student.BackEnd.Respond;

public class FrontGate {
    public void sendRequest(BackGate backGate,Request request) throws Exception {
        backGate.receiveRequest(request);
    }
    public Respond getRespond(BackGate backGate){
        return backGate.getRespond();
    }
}
