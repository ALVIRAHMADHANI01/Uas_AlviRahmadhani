package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PhoneReducer extends Reducer<Text, Text, NullWritable, Text>
{
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {
        String[] dataPhone = key.toString().split(",");
        String Name = dataPhone[0];
        String Brand = dataPhone[1];
        String Price = dataPhone[2];
        String Processor= dataPhone[3];

        int Ram = 0;
        int Memory = 0;

        for(Text value : values)
        {
            String[] Retail = value.toString().split("#");

            Ram += Integer.parseInt(Retail[0]);
            Memory += Integer.parseInt(Retail[1]);
        }

        context.write(NullWritable.get(), new Text(Name + "," + Brand + "," + "," + Price + "," + Processor + "," + String.valueOf(Ram) + "," + String.valueOf(Memory)));
    }
}