package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) throws Exception
    {
        Path input_dir = new Path("Alvi/input/");
        Path output_dir = new Path("Alvi/output/");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(output_dir))
            fs.delete(output_dir, true);

        Job data_PhoneJob = Job.getInstance(conf, "MapReduce Data Smartphone");
        data_PhoneJob.setJarByClass(App.class);
        data_PhoneJob.setReducerClass(PhoneReducer.class);
        data_PhoneJob.setMapOutputKeyClass(Text.class);
        data_PhoneJob.setMapOutputValueClass(Text.class);
        data_PhoneJob.setOutputKeyClass(NullWritable.class);
        data_PhoneJob.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(data_PhoneJob, input_dir);
        FileOutputFormat.setOutputPath(data_PhoneJob, output_dir);
        data_PhoneJob.waitForCompletion(true);
    }
}