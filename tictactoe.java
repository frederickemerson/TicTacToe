import java.util.*;
class tictactoe
{
    int check(String arr[])
    {
        if((arr[0].equals(arr[1]))&&(arr[0].equals(arr[2])))
        {
            if(arr[0].equals("X"))
            {
                return 1;
            }
            if(arr[0].equals("O"))
            {
                return 0;
            }
            return -1;
        }
        return -1;
    }

    boolean full(String board[][])
    {   
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                if(board[r][c].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }

    int winNot(String board[][])
    {
        for(int i=0;i<3;i++)
        {
            int a = check(board[i]);
            //.out.println("1 "+a);
            if(a>=0) return a;
        }
        for(int i=0;i<3;i++)
        {
            String col[]={board[0][i],board[1][i],board[2][i]};
            int a = check(col);
            //System.out.println("2 "+a);
            if(a>=0)return a;
        }

        String ld[]={board[0][0],board[1][1],board[2][2]};
        int a = check(ld);
        //System.out.println("3 "+a);
        if(a>=0)return a;

        String rd[]={board[0][2],board[1][1],board[2][0]};
        a = check(rd);
        //System.out.println("4 "+a);
        if(a>=0)return a;

        return -1;
    }

    void line()
    {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
    }

    void print(String board[][])
    {
        System.out.println("+---+---+---+");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("+---+---+---+");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("+---+---+---+");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("+---+---+---+");
    }

    void twoPlayer(String board[][])
    {
        System.out.println("Enter Player Name(X)");
        String ply1=new Scanner(System.in).nextLine();
        System.out.println("Enter Player Name(O)");
        String ply2=new Scanner(System.in).nextLine();
        line();
        System.out.println("Who Goes First(X/O):");
        String ply = new Scanner(System.in).nextLine();
        int c=0;

        while(!full(board))
        {
            print(board);
            String current=(ply.equals("X"))?ply1:ply2;
            System.out.println(current+"'s chance to play format(row,col) :");
            String inp = new Scanner(System.in).nextLine();
            line();
            int row = Integer.parseInt(inp.split(",")[0])-1;
            int col=Integer.parseInt(inp.split(",")[1])-1;
            if(board[row][col].equals(" ")){ board[row][col]=ply;}
            else
            {
                System.out.println("Value already placed");
                continue;
            }
            System.out.println(c);
            if(c<3)
            {
                c++;
            }
            else
            {
                int res = winNot(board);
               // System.out.println(res);
                if(res>=0)
                {
                    print(board);
                    line();
                    System.out.println(((res==1)?ply1:ply2)+" Wins !!");
                    break;
                }
            }
            ply=(ply.equals("X"))?"O":"X";
        }
        if(full(board))
        {
            System.out.println("DRAW");
        }
    }

    void computer(String board[][])
    {
        tictactoeAI ai = new tictactoeAI();
        ai.play(board,"X");
    }

    void main()
    {
        String board[][]={
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}
            };
        System.out.println("WELCOME TO FREDERICK'S TIC-TAC-TOE");
        System.out.println("Press 1: to play between your friends");
        System.out.println("Press 2: to play with computer");
        System.out.println("Enter your choice: ");
        int n = new Scanner(System.in).nextInt();
        System.out.print("\f");
        switch(n)
        {
            case 1:{
                twoPlayer(board);
                break;
            }
            case 2:
            {
                computer(board);
                break;
            }
            default:
            {
                System.out.println("Enter Correct Choice");
            }
        }

        //System.out.println(check(board[2]));
        //twoPlayer(board);
    }
}

