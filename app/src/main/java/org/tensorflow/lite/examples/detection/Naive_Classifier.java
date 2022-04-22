package org.tensorflow.lite.examples.detection;

import java.util.HashMap;
import java.util.Map;

public class Naive_Classifier {

    public Map<String, Float> build_map()
    {
        Map<String, Float> map = new HashMap<String, Float>();
        map.put("Rural/1",13877/29816f);
        map.put("Rural/2",1133/1938f);
        map.put("Rural/3",202/300f);
//**************************************
        map.put("Urban/1",13612/29816f);
        map.put("Urban/2",693/1938f);
        map.put("Urban/3",83/300f);
//**************************************
        map.put("Unknown/1",2327/29816f);
        map.put("Unknown/2",112/1938f);
        map.put("Unknown/3",15/300f);
//**************************************
        map.put("Non-Interstate/1",23905/29816f);
        map.put("Non-Interstate/2",1542/1938f);
        map.put("Non-Interstate/3",217/300f);
//**************************************
        map.put("Interstate/1",3472/29816f);
        map.put("Interstate/2",278/1938f);
        map.put("Interstate/3",66/300f);
//**************************************
        map.put("Unknown/1",2439/29816f);
        map.put("Unknown/2",118/1938f);
        map.put("Unknown/3",17/300f);
//**************************************
        map.put("Off Roadway/1",11969/29816f);
        map.put("Off Roadway/2",716/1938f);
        map.put("Off Roadway/3",100/300f);
//**************************************
        map.put("On Roadway/1",17788/29816f);
        map.put("On Roadway/2",1219/1938f);
        map.put("On Roadway/3",200/300f);
//**************************************
        map.put("Other/Unknown/1",59/29816f);
        map.put("Other/Unknown/2",3/1938f);
        map.put("Other/Unknown/3",0/300f);
//**************************************
        map.put("Non-Intersection/1",22478/29816f);
        map.put("Non-Intersection/2",1496/1938f);
        map.put("Non-Intersection/3",242/300f);
//**************************************
        map.put("Intersection/1",7274/29816f);
        map.put("Intersection/2",438/1938f);
        map.put("Intersection/3",58/300f);
//**************************************
        map.put("Unknown/1",63/29816f);
        map.put("Unknown/2",4/1938f);
        map.put("Unknown/3",0/300f);
//**************************************
        map.put("Not Collision/1",18932/29816f);
        map.put("Not Collision/2",809/1938f);
        map.put("Not Collision/3",108/300f);
//**************************************
        map.put("Angle/1",5230/29816f);
        map.put("Angle/2",459/1938f);
        map.put("Angle/3",67/300f);
//**************************************
        map.put("Head-On/1",2708/29816f);
        map.put("Head-On/2",438/1938f);
        map.put("Head-On/3",91/300f);
//**************************************
        map.put("Sideswipe/1",747/29816f);
        map.put("Sideswipe/2",64/1938f);
        map.put("Sideswipe/3",8/300f);
//**************************************
        map.put("Rear-End/1",2017/29816f);
        map.put("Rear-End/2",150/1938f);
        map.put("Rear-End/3",26/300f);
//**************************************
        map.put("Unknown/1",77/29816f);
        map.put("Unknown/2",6/1938f);
        map.put("Unknown/3",0/300f);
//**************************************
        map.put("Other/1",105/29816f);
        map.put("Other/2",12/1938f);
        map.put("Other/3",0/300f);
//**************************************
        map.put("Nighttime/1",15239/29816f);
        map.put("Nighttime/2",970/1938f);
        map.put("Nighttime/3",155/300f);
//**************************************
        map.put("Daytime/1",14341/29816f);
        map.put("Daytime/2",956/1938f);
        map.put("Daytime/3",144/300f);
//**************************************
        map.put("Unknown/1",236/29816f);
        map.put("Unknown/2",12/1938f);
        map.put("Unknown/3",1/300f);
//**************************************
        map.put("Restraint Not Used/1",6372/29816f);
        map.put("Restraint Not Used/2",411/1938f);
        map.put("Restraint Not Used/3",64/300f);
//**************************************
        map.put("Restraint Used/1",18026/29816f);
        map.put("Restraint Used/2",1154/1938f);
        map.put("Restraint Used/3",171/300f);
//**************************************
        map.put("Restraint Use Unknown/1",5418/29816f);
        map.put("Restraint Use Unknown/2",373/1938f);
        map.put("Restraint Use Unknown/3",65/300f);
//**************************************
        map.put("Light Truck - Pickup/1",5025/29816f);
        map.put("Light Truck - Pickup/2",330/1938f);
        map.put("Light Truck - Pickup/3",46/300f);
//**************************************
        map.put("Passenger Car/1",12279/29816f);
        map.put("Passenger Car/2",779/1938f);
        map.put("Passenger Car/3",112/300f);
//**************************************
        map.put("Large Truck/1",2285/29816f);
        map.put("Large Truck/2",124/1938f);
        map.put("Large Truck/3",25/300f);
//**************************************
        map.put("Other/Unknown/1",860/29816f);
        map.put("Other/Unknown/2",63/1938f);
        map.put("Other/Unknown/3",12/300f);
//**************************************
        map.put("Light Truck - Utility/1",4585/29816f);
        map.put("Light Truck - Utility/2",321/1938f);
        map.put("Light Truck - Utility/3",50/300f);
//**************************************
        map.put("Light Truck - Van/1",1371/29816f);
        map.put("Light Truck - Van/2",89/1938f);
        map.put("Light Truck - Van/3",13/300f);
//**************************************
        map.put("Motorcycle/1",3117/29816f);
        map.put("Motorcycle/2",214/1938f);
        map.put("Motorcycle/3",39/300f);
//**************************************
        map.put("Light Truck - Other/1",121/29816f);
        map.put("Light Truck - Other/2",8/1938f);
        map.put("Light Truck - Other/3",1/300f);
//**************************************
        map.put("Bus/1",173/29816f);
        map.put("Bus/2",10/1938f);
        map.put("Bus/3",2/300f);
//**************************************
        map.put("Front/1",18430/29816f);
        map.put("Front/2",1210/1938f);
        map.put("Front/3",196/300f);
//**************************************
        map.put("Rear/1",2462/29816f);
        map.put("Rear/2",162/1938f);
        map.put("Rear/3",32/300f);
//**************************************
        map.put("Right Side/1",2402/29816f);
        map.put("Right Side/2",143/1938f);
        map.put("Right Side/3",10/300f);
//**************************************
        map.put("Non-Collision/1",1881/29816f);
        map.put("Non-Collision/2",134/1938f);
        map.put("Non-Collision/3",14/300f);
//**************************************
        map.put("Left Side/1",2823/29816f);
        map.put("Left Side/2",161/1938f);
        map.put("Left Side/3",26/300f);
//**************************************
        map.put("Unknown/1",1239/29816f);
        map.put("Unknown/2",90/1938f);
        map.put("Unknown/3",14/300f);
//**************************************
        map.put("Other/1",579/29816f);
        map.put("Other/2",38/1938f);
        map.put("Other/3",8/300f);
//**************************************
        return map;
    }
    public int get_Severity(String a_ru,String a_inter,String a_relrd,String a_intsec,String a_mancol,String a_tod,String a_rest,String a_body,String a_imp1 )
    {

        float sev_1=1f,sev_2=1f,sev_3=1f;
        Map<String, Float> map=build_map();
        String []attr={a_ru,a_inter,a_relrd,a_intsec,a_mancol,a_tod,a_rest,a_body,a_imp1};
        for(int i=0;i< attr.length;i++)
        {
            sev_1*=map.get(attr[i]+"/1");
            sev_2*=map.get(attr[i]+"/2");
            sev_3*=map.get(attr[i]+"/3");

        }
        if(sev_1>sev_2&&sev_1>sev_3) {
            return 1;
        }
        else if(sev_2>sev_1&&sev_2>sev_3){
            return 2;
        }

        else {return 3;
        }

    }
}
