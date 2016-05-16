import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.Image;

public class maze extends Applet implements MouseListener
{
    int screen; //Global variable to hold what screen is to be displayed
    /*
    0 = TitleScreen
    1 = Help
    2 = level1
    3 = level2
    4 = level3
    */

    //Screen Images
    Image TitleScreen, help, level1, level2, level3, player, evilplayer; //Number Images for Display
    //Player's location ([x]*32+xx [y]*32+yy)
    int xx = 1;
    int yy = 0;

    //Enemy's location ([x]*32+xx2 [y]*32+yy2)
    int xx2 = 7;
    int yy2 = 1;

    public void init ()
    {
	resize (720, 480);
	screen = 0; //start on screen 0, the Title Screen
	addMouseListener (this);

	//Get the TitleScreen Picture

	TitleScreen = getImage (getDocumentBase (), "menu.jpg");

	//Get the HelpScreen picture

	help = getImage (getDocumentBase (), "helpscreen.jpg");

	//Get the Level 1 Picture

	level1 = getImage (getDocumentBase (), "screengame.jpg");

	//Get Player image

	player = getImage (getDocumentBase (), "testsubject.gif");

	//Get Evil Player image

	evilplayer = getImage (getDocumentBase (), "testsubjectebil.gif");

	//Get Level 2 Picture

	level2 = getImage (getDocumentBase (), "screengame2.jpg");

	//Get Level 3 Picture

	level3 = getImage (getDocumentBase (), "screengame3.jpg");







	Graphics g = getGraphics ();

	InitImages ();
	InitMaze ();
    }


    char maze[] [] = new char [11] [16];
    public void InitMaze ()
    {
	BufferedReader in;
	try
	{
	    in = new BufferedReader (new FileReader ("maze1.txt")); //Grid set for first level
	    for (int i = 0 ; i < 11 ; i++)
	    {
		String input = in.readLine ();
		for (int j = 0 ; j < 16 ; j++)
		{
		    maze [i] [j] = input.charAt (j);
		}
	    }

	    for (int i = 0 ; i < 11 ; i++)
	    {

		for (int j = 0 ; j < 16 ; j++)
		{
		    System.out.print (maze [i] [j]);
		}
		System.out.println ("");
	    }

	    in.close ();

	}

	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}


    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void death ()
    {
	if (xx == xx2 && yy == yy2)
	    System.out.println ("You died");

    }


    public boolean check (int x, int y)
    {
	//System.out.println (maze [y] [x] + ", (" + x + ", " + y + ")");

	//Checks if in bounds
	if (x < 0 || x >= 16)
	    return false;
	else if (y < 0 || y >= 11)
	    return false;

	// Checks if hits walls
	else if (maze [y] [x] == '0')
	    return true;
	/*  else if (maze [xx - 6] == maze [xx2] && maze [yy - 1] == maze [yy2])
	  {
	      death ();
	      return false;
	  } */
	else
	    return false;

    }


    public void mousePressed (MouseEvent e)
    {
	//get the x and y co-ordinates of the mouse
	int x = e.getX ();
	int y = e.getY ();


	//TITLE SCREEN
	if (screen == 0)
	{
	    if (x > 72 && x < 260 && y > 160 && y < 350) //Play button
	    {
		screen = 2;
		repaint ();
	    }
	    else if (x > 420 && x < 615 && y > 154 && y < 350) //Help button
	    {
		screen = 1;
		repaint ();
	    }
	}

	//HELP SCREEN
	if (screen == 1)
	{
	    if (x > 300 && x < 425 && y > 340 && y < 460) //Back button
	    {
		screen = 0;
		repaint ();
	    }
	}

	//LEVEL 1
	else if (screen == 2)
	{

	    if (x > 612 && x < 655 && y > 108 && y < 154) //up
	    {
		//stops going up out of box

		if (check (xx, yy - 1))
		{
		    yy -= 1;
		}

	    }

	    else if (x > 658 && x < 696 && y > 156 && y < 203) //right
	    {
		//stops going right out of box

		if (check (xx + 1, yy))
		{
		    xx += 1;
		}

	    }

	    else if (x > 614 && x < 653 && y > 206 && y < 249) //down
	    {
		//stops going down out of box

		if (check (xx, yy + 1))
		{
		    yy += 1;
		}
	    }

	    else if (x > 570 && x < 611 && y > 156 && y < 203) //left
	    {
		//stops going left out of box

		if (check (xx - 1, yy))
		{
		    xx -= 1;
		}

	    }

	    //Enemy movement
	    if (check (xx2, yy2 + 1))
		yy2 += 1;

	    else if (check (xx2 + 1, yy2))
		xx2 += 1;

	    else if (check (xx2, yy2 - 1))
		yy2 -= 1;

	    else if (check (xx2 - 1, yy2))
		xx2 -= 1;
	    death ();
	}

	// LEVEL 2
	else if (screen == 3)
	{

	    if (x > 612 && x < 655 && y > 108 && y < 154) //up
	    {
		//stops going up out of box

		if (check (xx, yy - 1))
		{
		    yy -= 1;
		}

	    }

	    else if (x > 658 && x < 696 && y > 156 && y < 203) //right
	    {
		//stops going right out of box

		if (check (xx + 1, yy))
		{
		    xx += 1;
		}

	    }

	    else if (x > 614 && x < 653 && y > 206 && y < 249) //down
	    {
		//stops going down out of box

		if (check (xx, yy + 1))
		{
		    yy += 1;
		}
		
	    }

	    else if (x > 570 && x < 611 && y > 156 && y < 203) //left
	    {
		//stops going left out of box

		if (check (xx - 1, yy))
		{
		    xx -= 1;
		}

	    }

	    //Generates random number for enemy player
	    int num = (int) (Math.random () * 4) + 1;

	    //Moves in direction based on generated number
	    if (check (xx2, yy2 + 1))
		yy2 += 1;

	    else if (check (xx2 + 1, yy2))
		xx2 += 1;

	    else if (check (xx2, yy2 - 1))
		yy2 -= 1;

	    else if (check (xx2 - 1, yy2))
		xx2 -= 1;
	    death ();
	}

	// LEVEL 3
	else if (screen == 4)
	{

	    if (x > 612 && x < 655 && y > 108 && y < 154) //up
	    {
		//stops going up out of box

		if (check (xx, yy - 1))
		{
		    yy -= 1;
		}

	    }

	    else if (x > 658 && x < 696 && y > 156 && y < 203) //right
	    {
		//stops going right out of box

		if (check (xx + 1, yy))
		{
		    xx += 1;
		}

	    }

	    else if (x > 614 && x < 653 && y > 206 && y < 249) //down
	    {
		//stops going down out of box

		if (check (xx, yy + 1))
		{
		    yy += 1;
		}
	    }

	    else if (x > 570 && x < 611 && y > 156 && y < 203) //left
	    {
		//stops going left out of box

		if (check (xx - 1, yy))
		{
		    xx -= 1;
		}
	    }

	    //Generates random number for enemy player
	    int num = (int) (Math.random () * 4) + 1;

	    //Moves in direction based on generated number
	    if (check (xx2, yy2 + 1))
		yy2 += 1;

	    else if (check (xx2 + 1, yy2))
		xx2 += 1;

	    else if (check (xx2, yy2 - 1))
		yy2 -= 1;

	    else if (check (xx2 - 1, yy2))
		xx2 -= 1;
	    death ();
	}

	repaint ();
    }




    public void InitImages ()
    { //Pre: All the images are there
	//Post: All the images are drawn offscreen, to ensure minimal load times during gameplay.
	Graphics g = getGraphics ();

	g.drawImage (TitleScreen, -100, -100, this);

	g.drawImage (level1, -100, -100, this);

	g.drawImage (level2, -100, -100, this);

	g.drawImage (level3, -100, -100, this);

	g.drawImage (player, -100, -100, this);

	g.drawImage (evilplayer, -100, -100, this);

	g.drawImage (help, -100, -100, this);



    }


    public void update (Graphics g)
    { //Overide the regular update method so it doesn't clear the screen before it draws (Gets rid of annoying white flicker)
	paint (g);
    }


    public void paint (Graphics g)
    {
	if (screen == 0) //Menu
	{
	    g.drawImage (TitleScreen, 0, 0, this);
	}

	else if (screen == 1) //Help
	{
	    g.drawImage (help, 0, 0, this);
	}

	else if (screen == 2) //Level 1
	{

	    g.drawImage (level1, 0, 0, this);

	    g.drawImage (player, xx * 32 + 22, yy * 32 + 105, this);

	    g.drawImage (evilplayer, xx2 * 32 + 22, yy2 * 32 + 105, this);

	    showStatus ("(" + xx + ", " + yy + "), (" + maze [yy] [xx] + ")" +
		    "(" + xx2 + ", " + yy2 + "), (" + maze [yy2] [xx2] + ")");

	    /*
	    int xx = 54;
	    int yy = 105;

	    int xx2 = 214/246;
	    int yy2 = 137;
	    */
	}
	else if (screen == 3) //Level 2
	{

	    g.drawImage (level2, 0, 0, this);

	    g.drawImage (player, xx * 32 + 22, yy * 32 + 105, this);

	}

	else if (screen == 4) //Level 3
	{

	    g.drawImage (level3, 0, 0, this);

	    g.drawImage (player, xx * 32, yy * 32 + 105, this);

	}
    }
}


