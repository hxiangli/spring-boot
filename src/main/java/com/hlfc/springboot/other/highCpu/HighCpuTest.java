package com.hlfc.springboot.other.highCpu;

import java.util.ArrayList;
import java.util.List;

public class HighCpuTest {
    public static void test() {
        List<HignCpu> cpus = new ArrayList<>();

        Thread highCpuThread = new Thread(()->{
            int i = 0;
            while (true){
                HignCpu cpu = new HignCpu("Java日知录",i);

                cpus.add(cpu);
                System.out.println("high cpu size:" + cpus.size());
                i ++;
            }
        });
        highCpuThread.setName("HignCpu");
        highCpuThread.start();
    }
}
