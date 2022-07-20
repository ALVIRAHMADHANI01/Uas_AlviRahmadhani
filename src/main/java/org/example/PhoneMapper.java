package org.example;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class PhoneMapper extends Mapper<Object, Text, Text, Text>
{
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException
    {
        try
        {
            if(value.toString().contains("Region"))
                return;
            else
            {
                String data = value.toString();
                String[] columns = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String Name = columns[1];
                String Brand = columns[3];
                String Price = columns[2];
                String Processor = columns[5];

                String Ram = columns[8];
                String Memory = columns[9];

                context.write(new Text(Name + "," + Brand + "," + Price + "," + Processor), new Text(Ram + "#" + Memory));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}