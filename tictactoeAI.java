
import java.util.*;
class tictactoeAI
{
    //String board[][];//turn;
    tictactoe obj = new tictactoe();

    /*tictactoeAI(String b[][],String turn)
    {
    this.board=b;
    //this.turn=turn;
    }*/

    int max(int arr[])
    {
        int g=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]>g) g=arr[i];
        }
        return g;
    }

    int getPosMax(int arr[])
    {
        int g=arr[0];int pos=0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]>g) {g=arr[i];pos=i;}
        }
        return pos;
    }

    int getPosMin(int arr[])
    {
        int g=arr[0];int pos=0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]<g) {g=arr[i];pos=i;}
        }
        return pos;
    } 
    
    int min(int arr[])
    {
        int s=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]<s)s=arr[i];
        }
        return s;
    }

    String[] normalize(String []a,int c)
    {
        String arr[] = new String[c];
        for(int i=0;i<c;i++)
        {
            arr[i]=a[i];
        }
        return arr;
    }

    String[] getSpaces(String board[][])
    {
        String arr[]=new String[9];
        int co=0;
        for(int r=0;r<board.length;r++)
        {
            for(int c=0;c<board[0].length;c++)
            {
                if(board[r][c].equals(" "))
                {
                    arr[co]=r+","+c;
                    co++;
                }
            }
        }
        if(co==0) return null;
        return normalize(arr,co);
    }

    int subMove(String board[][],String turn)
    {
        //System.out.println(turn);
        String spaces[] = getSpaces(board);
        if(spaces==null)
        {
            return obj.winNot(board);
        }
        int utilities[] = new int[spaces.length];
        for(int i=0;i<spaces.length;i++)
        {
            int r = Integer.parseInt(spaces[i].split(",")[0]);
            int c = Integer.parseInt(spaces[i].split(",")[1]);
            board[r][c]=turn;
            if(obj.winNot(board)==1){
                utilities[i]=spaces.length;
            }
            else {utilities[i]=spaces.length*subMove(board,turn.equals("X")?"O":"X");}
            board[r][c]=" ";
        }
        /*
        for(int i=0;i<utilities.length;i++)
        {
            System.out.println(utilities[i]+"  ");
        }
        */
        if(turn.equals("O"))
        {
            return min(utilities);
        }
        return max(utilities);
    }

    String mainMove(String board[][],String turn)
    {
        String spaces[] = getSpaces(board);
        int utilities[]=new int[spaces.length];
        for(int i=0;i<spaces.length;i++)
        {
            int r = Integer.parseInt(spaces[i].split(",")[0]);
            int c = Integer.parseInt(spaces[i].split(",")[1]);
            board[r][c]=turn;
            if(obj.winNot(board)==1)
            {
                return r+","+c;
            }
            utilities[i]=subMove(board,"O");
            board[r][c]=" ";
        }
        for(int i=0;i<utilities.length;i++)
        {
            System.out.println(utilities[i]);
        }
        return spaces[getPosMax(utilities)];
    }

    int[] input()
    {
        int arr[] = new int[2];
        System.out.println("Enter location(row,col) :");
        String inp = new Scanner(System.in).nextLine();
        arr[0] = Integer.parseInt(inp.split(",")[0])-1;
        arr[1] =Integer.parseInt(inp.split(",")[1])-1;
        return arr;
    }

    void play(String board[][],String player)
    {

       // System.out.println((player.equals("X"))?"":"Computer Moves:");

        int res=obj.winNot(board);
        if(res>=0)
        {
            obj.line();
            System.out.println(((res==1)?"Computer":"Human")+" Wins!!");
            return;
        }
        else
        {
            if(obj.full(board))
            {
                System.out.println("DRAW");
                return;
            }
            if(player.equals("O"))
            {
                obj.print(board);
                int rc[] = input();
                board[rc[0]][rc[1]]=player;
                obj.line();
                play(board,"X");
            }
            else
            {
                int l =(getSpaces(board)==null)?-1:(getSpaces(board)).length; 
                if(l>7)
                {
                    int r=new Random().nextInt(3);
                    int c=new Random().nextInt(3);
                    while(!board[r][c].equals(" "))
                    {
                        r=new Random().nextInt(3);
                        c=new Random().nextInt(3);
                    }
                    board[r][c]=player;
                }
                else
                {
                    String pos=mainMove(board,"X");
                    int r = Integer.parseInt(pos.split(",")[0]);
                    int c = Integer.parseInt(pos.split(",")[1]);
                    board[r][c]=player;
                }
                System.out.println("Computer Moves:");
                obj.print(board);
                obj.line();
                play(board,"O");
            }
        }
    }

    void main()
    {
        String board[][]={
                {"X","O","X"},
                {" ","O"," "},
                {" "," "," "}
            };
        System.out.println(mainMove(board,"X"));
        //play(board,"X");
    }
}