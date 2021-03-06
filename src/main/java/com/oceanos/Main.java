package com.oceanos;

import com.pi4j.io.gpio.*;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import com.pi4j.wiringpi.Gpio;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("<--Pi4J--> GPIO Control Example ... started.");

            // create gpio controller
            final GpioController gpio = GpioFactory.getInstance();

            // provision gpio pin #01 as an output pin and turn on
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

            // set shutdown state for this pin
            pin.setShutdownOptions(true, PinState.LOW);

            System.out.println("--> GPIO state should be: ON");

            Thread.sleep(5000);

            // turn off gpio pin #01
            pin.low();
            System.out.println("--> GPIO state should be: OFF");

            Thread.sleep(5000);

            // toggle the current state of gpio pin #01 (should turn on)
            pin.toggle();
            System.out.println("--> GPIO state should be: ON");

            Thread.sleep(5000);

            // toggle the current state of gpio pin #01  (should turn off)
            pin.toggle();
            System.out.println("--> GPIO state should be: OFF");

            Thread.sleep(5000);

            // turn on gpio pin #01 for 1 second and then off
            System.out.println("--> GPIO state should be: ON for only 1 second");
            pin.pulse(1000, true); // set second argument to 'true' use a blocking call

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();

            System.out.println("Exiting ControlGpioExample");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        try {

            printHwDetails();
        } catch (Exception e) {
            System.out.println("You're not running on The PI Platform");
        }

    }

    private static void printHwDetails() throws IOException, InterruptedException, ParseException {

            int x =100;

            // display a few of the available system information properties
            System.out.println("----------------------------------------------------");
            System.out.println("HARDWARE INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("Serial Number     :  " + SystemInfo.getSerial());
            System.out.println("CPU Revision      :  " + SystemInfo.getCpuRevision());
            System.out.println("CPU Architecture  :  " + SystemInfo.getCpuArchitecture());
            System.out.println("CPU Part          :  " + SystemInfo.getCpuPart());
            System.out.println("CPU Temperature   :  " + SystemInfo.getCpuTemperature());
            System.out.println("CPU Core Voltage  :  " + SystemInfo.getCpuVoltage());
            System.out.println("CPU Model Name    :  " + SystemInfo.getModelName());
            System.out.println("Processor         :  " + SystemInfo.getProcessor());
            System.out.println("Hardware Revision :  " + SystemInfo.getRevision());
            System.out.println("Is Hard Float ABI :  " + SystemInfo.isHardFloatAbi());
            System.out.println("Board Type        :  " + SystemInfo.getBoardType().name());

            System.out.println("----------------------------------------------------");
            System.out.println("MEMORY INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("Total Memory      :  " + SystemInfo.getMemoryTotal());
            System.out.println("Used Memory       :  " + SystemInfo.getMemoryUsed());
            System.out.println("Free Memory       :  " + SystemInfo.getMemoryFree());
            System.out.println("Shared Memory     :  " + SystemInfo.getMemoryShared());
            System.out.println("Memory Buffers    :  " + SystemInfo.getMemoryBuffers());
            System.out.println("Cached Memory     :  " + SystemInfo.getMemoryCached());
            System.out.println("SDRAM_C Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_C());
            System.out.println("SDRAM_I Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_I());
            System.out.println("SDRAM_P Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_P());

            System.out.println("----------------------------------------------------");
            System.out.println("OPERATING SYSTEM INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("OS Name           :  " + SystemInfo.getOsName());
            System.out.println("OS Version        :  " + SystemInfo.getOsVersion());
            System.out.println("OS Architecture   :  " + SystemInfo.getOsArch());
            System.out.println("OS Firmware Build :  " + SystemInfo.getOsFirmwareBuild());
            System.out.println("OS Firmware Date  :  " + SystemInfo.getOsFirmwareDate());

            System.out.println("----------------------------------------------------");
            System.out.println("JAVA ENVIRONMENT INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("Java Vendor       :  " + SystemInfo.getJavaVendor());
            System.out.println("Java Vendor URL   :  " + SystemInfo.getJavaVendorUrl());
            System.out.println("Java Version      :  " + SystemInfo.getJavaVersion());
            System.out.println("Java VM           :  " + SystemInfo.getJavaVirtualMachine());
            System.out.println("Java Runtime      :  " + SystemInfo.getJavaRuntime());

            System.out.println("----------------------------------------------------");
            System.out.println("NETWORK INFO");
            System.out.println("----------------------------------------------------");

            // display some of the network information
            System.out.println("Hostname          :  " + NetworkInfo.getHostname());
            for (String ipAddress : NetworkInfo.getIPAddresses())
                System.out.println("IP Addresses      :  " + ipAddress);
            for (String fqdn : NetworkInfo.getFQDNs())
                System.out.println("FQDN              :  " + fqdn);
            for (String nameserver : NetworkInfo.getNameservers())
                System.out.println("Nameserver        :  " + nameserver);

            System.out.println("----------------------------------------------------");
            System.out.println("CODEC INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("H264 Codec Enabled:  " + SystemInfo.getCodecH264Enabled());
            System.out.println("MPG2 Codec Enabled:  " + SystemInfo.getCodecMPG2Enabled());
            System.out.println("WVC1 Codec Enabled:  " + SystemInfo.getCodecWVC1Enabled());

            System.out.println("----------------------------------------------------");
            System.out.println("CLOCK INFO");
            System.out.println("----------------------------------------------------");
            System.out.println("ARM Frequency     :  " + SystemInfo.getClockFrequencyArm());
            System.out.println("CORE Frequency    :  " + SystemInfo.getClockFrequencyCore());
            System.out.println("H264 Frequency    :  " + SystemInfo.getClockFrequencyH264());
            System.out.println("ISP Frequency     :  " + SystemInfo.getClockFrequencyISP());
            System.out.println("V3D Frequency     :  " + SystemInfo.getClockFrequencyV3D());
            System.out.println("UART Frequency    :  " + SystemInfo.getClockFrequencyUART());
            System.out.println("PWM Frequency     :  " + SystemInfo.getClockFrequencyPWM());
            System.out.println("EMMC Frequency    :  " + SystemInfo.getClockFrequencyEMMC());
            System.out.println("Pixel Frequency   :  " + SystemInfo.getClockFrequencyPixel());
            System.out.println("VEC Frequency     :  " + SystemInfo.getClockFrequencyVEC());
            System.out.println("HDMI Frequency    :  " + SystemInfo.getClockFrequencyHDMI());
            System.out.println("DPI Frequency     :  " + SystemInfo.getClockFrequencyDPI());


            System.out.println();
            System.out.println();

    }
}