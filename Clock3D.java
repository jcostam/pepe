// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Clock3D.oj111

import java.applet.Applet;
import java.awt.*;
import java.util.Date;
import java.util.Random;

public class Clock3D extends Applet
    implements Runnable
{

    public void start()
    {
        if(!_802)
        {
            if(drawThread == null)
                drawThread = new Thread(this);
            drawThread.start();
        }
    }

    public String[][] getParameterInfo()
    {
        String as[][] = {
            {
                "More Info", "at", "http://www.dataway.ch/~bennet/java/"
            }
        };
        return as;
    }

    public void stop()
    {
        drawThread = null;
        repaint();
    }

    private Color _611(String s)
    {
        boolean flag = false;
        try
        {
            int i;
            if(s.startsWith("0x"))
                i = Integer.parseInt(s.substring(2), 16);
            else
            if(s.startsWith("#"))
                i = Integer.parseInt(s.substring(1), 16);
            else
            if(s.startsWith("0") && s.length() > 1)
                i = Integer.parseInt(s.substring(1), 8);
            else
                i = Integer.parseInt(s, 10);
            return new Color(i);
        }
        catch(NumberFormatException _ex)
        {
            return null;
        }
    }

    private int _925(int i)
    {
        int j = _726[(i + 90) % 180];
        if(i > 90 && i < 270)
            j *= -1;
        return j;
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(_802)
        {
            _802 = false;
            start();
        } else
        {
            _802 = true;
            stop();
        }
        return true;
    }

    private synchronized void _457(Graphics g)
    {
        for(int i1 = 0; i1 < _563; i1++)
        {
            int k = _165[i1];
            int l = _710 / _513[k];
            if(l < 3)
                l = 3;
            int i = (_988 + (_014[k] << 7) / _513[k]) - (l >> 1);
            int j = (_445 + (_303[k] << 7) / _513[k]) - (l >> 1);
            g.setColor(_352[(_513[k] - _858) / _174]);
            g.fillOval(i, j, l, l);
        }

    }

    private int _955(int i)
    {
        int j = _726[i % 180];
        if(i > 180)
            j *= -1;
        return j;
    }

    public String getAppletInfo()
    {
        return "3D Clock V1.11 by Bennet Uk (bennet@dataway.ch)";
    }

    private void _999()
    {
        int i = 0;
        do
        {
            for(int j = 2; j <= _057[i][0]; j += 2)
                _057[i][j] -= 3;

        } while(++i < 12);
    }

    private synchronized void _266()
    {
        _808.setTime(System.currentTimeMillis());
        if(_808.getSeconds() != _008)
        {
            _008 = _808.getSeconds();
            _004 = _808.getMinutes();
            _119 = _808.getHours();
            _837();
        }
        _377 += _607;
        if(_377 > 0x16800)
            _377 -= 0x16800;
        _531 += _601;
        if(_531 > 0x16800)
            _531 -= 0x16800;
        _571 += _166;
        if(_571 > 0x16800)
            _571 -= 0x16800;
        int i = _377 >> 8;
        int l = _531 >> 8;
        int j1 = _571 >> 8;
        int l1 = (_925(i) * _925(j1) >> 8) + (_955(i) * _955(l) * _955(j1) >> 16);
        int i2 = _925(l) * _955(j1) >> 8;
        int j2 = (_925(i) * _955(l) * _955(j1) >> 16) - (_955(i) * _925(j1) >> 8);
        int k2 = (_955(i) * _955(l) * _925(j1) >> 16) - (_955(j1) * _925(i) >> 8);
        int l2 = _925(l) * _925(j1) >> 8;
        int i3 = (_955(i) * _955(j1) >> 8) + (_925(i) * _955(l) * _925(j1) >> 16);
        int j3 = _955(i) * _925(l) >> 8;
        int k3 = -_955(l);
        int l3 = _925(i) * _925(l) >> 8;
        for(int j = 0; j < _563; j++)
        {
            _014[j] = _147[j] * l1 + _364[j] * i2 + _091[j] * j2 >> 8;
            _303[j] = _147[j] * k2 + _364[j] * l2 + _091[j] * i3 >> 8;
            _513[j] = (_147[j] * j3 + _364[j] * k3 + _091[j] * l3 >> 8) + _746;
        }

        for(int k = 0; k < _563 - 1; k++)
        {
            for(int i1 = k + 1; i1 < _563; i1++)
                if(_513[_165[i1]] > _513[_165[k]])
                {
                    int k1 = _165[i1];
                    _165[i1] = _165[k];
                    _165[k] = k1;
                }

        }

    }

    public Clock3D()
    {
        _808 = new Date();
    }

    public void run()
    {
        while(Thread.currentThread() == drawThread) 
        {
            long l = System.currentTimeMillis() + (long)_193;
            repaint();
            try
            {
                Thread.sleep(Math.max(0L, l - System.currentTimeMillis()));
            }
            catch(InterruptedException _ex)
            {
                return;
            }
        }
    }

    private void _837()
    {
        int l3 = 0;
        int i8 = 0;
        if(_479 == 1)
        {
            if(_119 == 12)
                i8 = 1;
            if(_119 == 0)
            {
                _119 = 12;
                i8 = 0;
            }
            if(_119 > 12)
            {
                _119 -= 12;
                i8 = 1;
            }
        }
        switch(_584)
        {
        case 2: // '\002'
            int k7 = -(_663 / 2) + 1;
            int i4;
            for(int i = 1; i <= _057[i4 = _119 / 10][0]; i += 2)
            {
                _364[l3] = (_057[i4][i] + k7) * _350;
                _091[l3] = _350 * _057[i4][i + 1] * _955(((k7 + _057[i4][i]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[i4][i + 1] * _925(((k7 + _057[i4][i]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            k7 += 6;
            int j4;
            for(int j = 1; j <= _057[j4 = _119 % 10][0]; j += 2)
            {
                _364[l3] = (_057[j4][j] + k7) * _350;
                _091[l3] = _350 * _057[j4][j + 1] * _955(((k7 + _057[j4][j]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[j4][j + 1] * _925(((k7 + _057[j4][j]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            _364[l3] = (k7 + 6) * _350;
            _091[l3] = -(_350 * _955(((k7 + 6) * _895 + 3600) % 360)) >> 8;
            _147[l3] = _350 * _925(((k7 + 6) * _895 + 3600) % 360) >> 8;
            l3++;
            _364[l3] = (k7 + 6) * _350;
            _091[l3] = _350 * _955(((k7 + 6) * _895 + 3600) % 360) >> 8;
            _147[l3] = -(_350 * _925(((k7 + 6) * _895 + 3600) % 360)) >> 8;
            l3++;
            k7 += 8;
            int k4;
            for(int k = 1; k <= _057[k4 = _004 / 10][0]; k += 2)
            {
                _364[l3] = (_057[k4][k] + k7) * _350;
                _091[l3] = _350 * _057[k4][k + 1] * _955(((k7 + _057[k4][k]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[k4][k + 1] * _925(((k7 + _057[k4][k]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            k7 += 6;
            int l4;
            for(int l = 1; l <= _057[l4 = _004 % 10][0]; l += 2)
            {
                _364[l3] = (_057[l4][l] + k7) * _350;
                _091[l3] = _350 * _057[l4][l + 1] * _955(((k7 + _057[l4][l]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[l4][l + 1] * _925(((k7 + _057[l4][l]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            _364[l3] = (k7 + 6) * _350;
            _091[l3] = -(_350 * _955(((k7 + 6) * _895 + 3600) % 360)) >> 8;
            _147[l3] = _350 * _925(((k7 + 6) * _895 + 3600) % 360) >> 8;
            l3++;
            _364[l3] = (k7 + 6) * _350;
            _091[l3] = _350 * _955(((k7 + 6) * _895 + 3600) % 360) >> 8;
            _147[l3] = -(_350 * _925(((k7 + 6) * _895 + 3600) % 360)) >> 8;
            l3++;
            k7 += 8;
            int i5;
            for(int i1 = 1; i1 <= _057[i5 = _008 / 10][0]; i1 += 2)
            {
                _364[l3] = (_057[i5][i1] + k7) * _350;
                _091[l3] = _350 * _057[i5][i1 + 1] * _955(((k7 + _057[i5][i1]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[i5][i1 + 1] * _925(((k7 + _057[i5][i1]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            k7 += 6;
            int j5;
            for(int j1 = 1; j1 <= _057[j5 = _008 % 10][0]; j1 += 2)
            {
                _364[l3] = (_057[j5][j1] + k7) * _350;
                _091[l3] = _350 * _057[j5][j1 + 1] * _955(((k7 + _057[j5][j1]) * _895 + 3600) % 360) >> 8;
                _147[l3] = -(_350 * _057[j5][j1 + 1] * _925(((k7 + _057[j5][j1]) * _895 + 3600) % 360)) >> 8;
                l3++;
            }

            if(_479 == 1)
            {
                k7 += 6;
                int k5;
                for(int k1 = 1; k1 <= _057[k5 = i8 + 10][0]; k1 += 2)
                {
                    _364[l3] = (_057[k5][k1] + k7) * _350;
                    _091[l3] = _350 * _057[k5][k1 + 1] * _955(((k7 + _057[k5][k1]) * _895 + 3600) % 360) >> 8;
                    _147[l3] = -(_350 * _057[k5][k1 + 1] * _925(((k7 + _057[k5][k1]) * _895 + 3600) % 360)) >> 8;
                    l3++;
                }

            }
            break;

        case 1: // '\001'
        default:
            int l7 = 0;
            int l5;
            for(int l1 = 1; l1 <= _057[l5 = _119 / 10][0]; l1 += 2)
            {
                _364[l3] = _057[l5][l1 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[l5][l1] * _895);
                _091[l3] = -_822 * _925(l7 + _057[l5][l1] * _895);
                l3++;
            }

            l7 += 6 * _895;
            int i6;
            for(int i2 = 1; i2 <= _057[i6 = _119 % 10][0]; i2 += 2)
            {
                _364[l3] = _057[i6][i2 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[i6][i2] * _895);
                _091[l3] = -_822 * _925(l7 + _057[i6][i2] * _895);
                l3++;
            }

            _364[l3] = -_350;
            _147[l3] = _822 * _955(l7 + 6 * _895);
            _091[l3] = -_822 * _925(l7 + 6 * _895);
            l3++;
            _364[l3] = _350;
            _147[l3] = _822 * _955(l7 + 6 * _895);
            _091[l3] = -_822 * _925(l7 + 6 * _895);
            l3++;
            l7 += 8 * _895;
            int j6;
            for(int j2 = 1; j2 <= _057[j6 = _004 / 10][0]; j2 += 2)
            {
                _364[l3] = _057[j6][j2 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[j6][j2] * _895);
                _091[l3] = -_822 * _925(l7 + _057[j6][j2] * _895);
                l3++;
            }

            l7 += 6 * _895;
            int k6;
            for(int k2 = 1; k2 <= _057[k6 = _004 % 10][0]; k2 += 2)
            {
                _364[l3] = _057[k6][k2 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[k6][k2] * _895);
                _091[l3] = -_822 * _925(l7 + _057[k6][k2] * _895);
                l3++;
            }

            _364[l3] = _350;
            _147[l3] = _822 * _955(l7 + 6 * _895);
            _091[l3] = -_822 * _925(l7 + 6 * _895);
            l3++;
            _364[l3] = -_350;
            _147[l3] = _822 * _955(l7 + 6 * _895);
            _091[l3] = -_822 * _925(l7 + 6 * _895);
            l3++;
            l7 += 8 * _895;
            int l6;
            for(int l2 = 1; l2 <= _057[l6 = _008 / 10][0]; l2 += 2)
            {
                _364[l3] = _057[l6][l2 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[l6][l2] * _895);
                _091[l3] = -_822 * _925(l7 + _057[l6][l2] * _895);
                l3++;
            }

            l7 += 6 * _895;
            int i7;
            for(int i3 = 1; i3 <= _057[i7 = _008 % 10][0]; i3 += 2)
            {
                _364[l3] = _057[i7][i3 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[i7][i3] * _895);
                _091[l3] = -_822 * _925(l7 + _057[i7][i3] * _895);
                l3++;
            }

            if(_479 != 1)
                break;
            l7 += 6 * _895;
            int j7;
            for(int j3 = 1; j3 <= _057[j7 = i8 + 10][0]; j3 += 2)
            {
                _364[l3] = _057[j7][j3 + 1] * _350;
                _147[l3] = _822 * _955(l7 + _057[j7][j3] * _895);
                _091[l3] = -_822 * _925(l7 + _057[j7][j3] * _895);
                l3++;
            }

            break;
        }
        _563 = l3;
        for(int k3 = 0; k3 < _563; k3++)
            _165[k3] = k3;

    }

    public void init()
    {
        int i = 0;
        _783 = new Random();
        String s = getParameter("fps");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _193 = i <= 0 ? 75 : 1000 / i;
        i = 0;
        s = getParameter("style");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _584 = i <= 0 ? 1 : i;
        i = 0;
        s = getParameter("12hour");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _479 = i != 1 ? 0 : i;
        i = 0;
        s = getParameter("pixd");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _350 = i <= 0 ? 256 : i << 4;
        i = 0;
        s = getParameter("pixangle");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _895 = i <= 0 ? 8 : i;
        i = 0;
        s = getParameter("radius");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _822 = i <= 0 ? 15 : i;
        i = 0;
        s = getParameter("a1");
        try
        {
            if(s != null)
                i = Integer.parseInt(s);
        }
        catch(Exception _ex) { }
        _746 = i <= 0 ? 5000 : i;
        i = 0;
        s = getParameter("rotx");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _601 = i;
        i = 1024;
        s = getParameter("roty");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _607 = i;
        i = 0;
        s = getParameter("rotz");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _166 = i;
        i = 0;
        s = getParameter("irotx");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _531 = i;
        i = 0;
        s = getParameter("iroty");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _377 = i;
        i = 0;
        s = getParameter("irotz");
        try
        {
            if(s != null)
                i = (int)((new Double(s)).doubleValue() * 256D);
        }
        catch(Exception _ex) { }
        if(i < 0)
            i += 0x16800;
        _571 = i;
        _726 = new int[181];
        i = 0;
        do
            _726[i] = (int)(Math.sin(((double)i / 180D) * 3.1415926535897931D) * 256D);
        while(++i < 180);
        s = getParameter("bgcolor");
        if(s != null)
            _450 = _611(s);
        if(_450 == null)
            _450 = new Color(0);
        _352 = new Color[65];
        s = getParameter("color");
        if(s != null)
            _352[0] = _611(s);
        if(_352[0] == null)
            _352[0] = new Color(0xff0000);
        double ad[] = new double[3];
        double ad1[] = new double[3];
        ad[0] = _352[0].getRed();
        ad[1] = _352[0].getGreen();
        ad[2] = _352[0].getBlue();
        ad1[0] = ((double)_450.getRed() - ad[0]) / 110D;
        ad1[1] = ((double)_450.getGreen() - ad[1]) / 110D;
        ad1[2] = ((double)_450.getBlue() - ad[2]) / 110D;
        i = 1;
        do
            _352[i] = new Color((int)(ad[0] += ad1[0]), (int)(ad[1] += ad1[1]), (int)(ad[2] += ad1[2]));
        while(++i < 65);
        _014 = new int[150];
        _303 = new int[150];
        _513 = new int[150];
        _165 = new int[150];
        _147 = new int[150];
        _364 = new int[150];
        _091 = new int[150];
        _710 = _350 * 120;
        switch(_479)
        {
        case 1: // '\001'
            _663 = 51;
            break;

        case 0: // '\0'
        default:
            _663 = 40;
            break;
        }
        switch(_584)
        {
        case 2: // '\002'
            _174 = _350 * _663;
            break;

        case 1: // '\001'
        default:
            _174 = _822 << 8;
            break;
        }
        _858 = _746 - _174;
        _174 /= 32;
        _999();
    }

    public void update(Graphics g)
    {
        if(_875 == null)
        {
            _519 = size();
            _301 = createImage(_519.width, _519.height);
            _875 = _301.getGraphics();
            _988 = _519.width / 2;
            _445 = _519.height / 2;
        }
        _875.setColor(_450);
        _875.fillRect(0, 0, _519.width, _519.height);
        _266();
        _457(_875);
        g.drawImage(_301, 0, 0, this);
    }

    public void paint(Graphics g)
    {
        if(_301 != null)
            g.drawImage(_301, 0, 0, this);
    }

    private static final int _001 = 150;
    private int _563;
    private int _193;
    private Date _808;
    private int _584;
    private int _479;
    private int _350;
    private int _895;
    private int _822;
    private int _746;
    private int _174;
    private int _858;
    private int _710;
    private int _513[];
    private int _303[];
    private int _014[];
    private int _091[];
    private int _364[];
    private int _147[];
    private int _165[];
    private int _988;
    private int _445;
    private int _119;
    private int _004;
    private int _008;
    private int _377;
    private int _531;
    private int _571;
    private int _601;
    private int _607;
    private int _166;
    private int _663;
    private Color _450;
    private Color _352[];
    private int _057[][] = {
        {
            38, 0, 1, 0, 2, 0, 3, 0, 4, 0, 
            5, 1, 0, 1, 4, 1, 6, 2, 0, 2, 
            3, 2, 6, 3, 0, 3, 2, 3, 6, 4, 
            1, 4, 2, 4, 3, 4, 4, 4, 5
        }, {
            20, 1, 1, 1, 6, 2, 0, 2, 1, 2, 
            2, 2, 3, 2, 4, 2, 5, 2, 6, 3, 
            6
        }, {
            28, 0, 1, 0, 6, 1, 0, 1, 5, 1, 
            6, 2, 0, 2, 4, 2, 6, 3, 0, 3, 
            3, 3, 6, 4, 1, 4, 2, 4, 6
        }, {
            28, 0, 0, 0, 5, 1, 0, 1, 6, 2, 
            0, 2, 2, 2, 6, 3, 0, 3, 1, 3, 
            3, 3, 6, 4, 0, 4, 4, 4, 5
        }, {
            28, 0, 3, 0, 4, 1, 2, 1, 4, 2, 
            1, 2, 4, 3, 0, 3, 1, 3, 2, 3, 
            3, 3, 4, 3, 5, 3, 6, 4, 4
        }, {
            34, 0, 0, 0, 1, 0, 2, 0, 5, 1, 
            0, 1, 2, 1, 6, 2, 0, 2, 2, 2, 
            6, 3, 0, 3, 2, 3, 6, 4, 0, 4, 
            3, 4, 4, 4, 5
        }, {
            30, 0, 2, 0, 3, 0, 4, 0, 5, 1, 
            1, 1, 3, 1, 6, 2, 0, 2, 3, 2, 
            6, 3, 0, 3, 3, 3, 6, 4, 4, 4, 
            5
        }, {
            22, 0, 0, 1, 0, 1, 4, 1, 5, 1, 
            6, 2, 0, 2, 3, 3, 0, 3, 2, 4, 
            0, 4, 1
        }, {
            34, 0, 1, 0, 2, 0, 4, 0, 5, 1, 
            0, 1, 3, 1, 6, 2, 0, 2, 3, 2, 
            6, 3, 0, 3, 3, 3, 6, 4, 1, 4, 
            2, 4, 4, 4, 5
        }, {
            30, 0, 1, 0, 2, 1, 0, 1, 3, 1, 
            6, 2, 0, 2, 3, 2, 6, 3, 0, 3, 
            3, 3, 5, 4, 1, 4, 2, 4, 3, 4, 
            4
        }, {
            50, 0, 3, 0, 4, 0, 5, 0, 6, 1, 
            2, 1, 4, 2, 2, 2, 4, 3, 3, 3, 
            4, 3, 5, 3, 6, 5, 2, 5, 3, 5, 
            4, 5, 5, 5, 6, 6, 3, 7, 4, 8, 
            3, 9, 2, 9, 3, 9, 4, 9, 5, 9, 
            6
        }, {
            46, 0, 2, 0, 3, 0, 4, 0, 5, 0, 
            6, 1, 2, 1, 4, 2, 2, 2, 4, 3, 
            3, 5, 2, 5, 3, 5, 4, 5, 5, 5, 
            6, 6, 3, 7, 4, 8, 3, 9, 2, 9, 
            3, 9, 4, 9, 5, 9, 6
        }
    };
    private Random _783;
    Thread drawThread;
    private boolean _802;
    private Dimension _519;
    private Image _301;
    private Graphics _875;
    private int _726[];
}

