package managers;

import data.Dot;


public class JsonParser {




    public static Dot parseJson(String json) {
        var elements = json.split(":");

        double[] values = new double[3];
        try {
            values[0] = Integer.parseInt((elements[1].split(",")[0]));
            values[1] = Double.parseDouble((elements[2].split(",")[0]).replace("\"", ""));
            values[2] = Integer.parseInt(elements[3].split("}")[0].replace("}", ""));

            return new Dot((int) values[0], values[1], (int) values[2], true);
        } catch (Exception e) {
           return null;
        }

    }



}
