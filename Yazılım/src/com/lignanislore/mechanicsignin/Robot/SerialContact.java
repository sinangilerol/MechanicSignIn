package com.lignanislore.mechanicsignin.Robot;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialContact
{
    public static String lastData;

    public SerialContact()
    {
        super();
        lastData = "";
    }

    public void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("HATA");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();


                (new Thread(new SerialReader(in))).start();

            }
        }
    }

    public static class SerialReader implements Runnable
    {
        InputStream in;

        public SerialReader ( InputStream in )
        {
            this.in = in;
        }

        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;

            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {

                    lastData = new String(buffer, 0, len);
                    if (!lastData.isEmpty()) {
                        switch (lastData){
                            case "1": RobotControl.yazmayaBasla(1); break;
                            case "2": RobotControl.yazmayaBasla(2); break;
                            case "3": RobotControl.yazmayaBasla(3); break;
                            case "4": RobotControl.yazmayaBasla(4); break;
                        }
                    }



                }



            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }




    }


}