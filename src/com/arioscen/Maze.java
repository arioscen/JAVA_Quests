package com.arioscen;

import java.util.ArrayList;
public class Maze {

	public static void main(String[] args) {
		int [][] maze = new int[][]{
			{2, 2, 2, 2, 2, 2, 2}, 
            {0, 0, 0, 0, 0, 0, 2}, 
            {2, 0, 2, 0, 2, 0, 2}, 
            {2, 0, 0, 2, 0, 2, 2}, 
            {2, 2, 0, 2, 0, 2, 2}, 
            {2, 0, 0, 0, 0, 0, 2}, 
            {2, 2, 2, 2, 2, 0, 2}
		};
		//計算maze有多少個資料
		//maze.length表示maze有幾個row
		int maze_length = 0;
		for (int i = 0; i < maze.length; i++){
			maze_length += maze[i].length;
		}
		//將二維陣列maze轉成一維陣列
		int maze_count = 0;
		int [] maze_c = new int[maze_length];
		for (int [] row : maze){
			for (int column: row){
			maze_c[maze_count] = column;
			maze_count += 1;
			}
		}
		//現在位置
		int place = 7;
		//移動，上:-7; 下:+7; 左:-1; 右:+1
		//限制:0<=place<=48
		int top = maze[0].length-1;
		int last = maze[maze.length-1].length;
		int low = maze_length-last;
		//出口的條件:1.該位置的值等於0  2.在迷宮邊界
		//place <= top || place >= low || (place-6)%7 == 0 || place%7 == 0
		//延伸:如果迷宮形狀不規則?
		
		//為還有其他路的點儲存savepoint
		//製作每個點檢查表，表示上下左右是否可以走，0可; 2不可
		int [][] savepoint = new int[maze_length][4];
		//製作路徑記錄陣列
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		//記錄第一步
		arrlist.add(7);
		//為arrlist在迴圈內使用上的需要，多增加了一個值，做完回圈之後刪除
		arrlist.add(7);	
		//開始走迷宮
		while (true){
			if (place != 7 && (place <= top || place >= low || (place-6)%7==0 || place%7 == 0)){
				System.out.println("end");
				break;
			}
			//檢查四周可走的路
			if ((place+1) <= 48 && maze_c[place+1] == 2){
				savepoint[place][3] = 2;
			}
			if ((place+7) <= 48 && maze_c[place+7] == 2){
				savepoint[place][1] = 2;
			}
			if ((place-1) >= 0 && maze_c[place-1] == 2){
				savepoint[place][2] = 2;
			}
			if ((place-7) >= 0 && maze_c[place-7] == 2){
				savepoint[place][0] = 2;
			}
			//堵住上一次走的路，避免走回頭路
			if (place-arrlist.get(arrlist.size()-2) == 1){
				savepoint[place][2] = 2;
			}
			else if (place-arrlist.get(arrlist.size()-2) == 7){
				savepoint[place][0] = 2;
			}
			else if (place-arrlist.get(arrlist.size()-2) == -1){
				savepoint[place][3] = 2;
			}
			else if (place-arrlist.get(arrlist.size()-2) == -7){
				savepoint[place][1] = 2;
			}
			//開始按照可走方向前進，每次前進記錄路徑
			int placecheck = place;
			for (int i = 0; i <=3; i++){
				if (savepoint[place][i] == 0){
					if (i==0){
						arrlist.add(place-7);
						place -= 7;
						break;
					}
					else if (i==1){
						arrlist.add(place+7);
						place += 7;
						break;
					}
					else if (i==2){
						arrlist.add(place-1);
						place -= 1;
						break;
					}
					else if (i==3){
						arrlist.add(place+1);
						place += 1;
						break;
					}				
				}
			}
				//無路可走，回到上一步，並把不通的路堵住，刪除路徑記錄
			if (placecheck == place){
				if (place-arrlist.get(arrlist.size()-2) == 1){
					savepoint[arrlist.get(arrlist.size()-2)][3] = 2;
				}
				else if (place-arrlist.get(arrlist.size()-2) == 7){
					savepoint[arrlist.get(arrlist.size()-2)][1] = 2;
				}
				else if (place-arrlist.get(arrlist.size()-2) == -1){
					savepoint[arrlist.get(arrlist.size()-2)][2] = 2;
				}
				else if (place-arrlist.get(arrlist.size()-2) == -7){
					savepoint[arrlist.get(arrlist.size()-2)][0] = 2;
				}
				place = arrlist.get(arrlist.size()-2);
				arrlist.remove(arrlist.size()-1);
			}
			//檢查現在位置
//			System.out.println(place+",");			
		}
		//刪除使用上多列出的第一項
		arrlist.remove(0);
		//打印一維路徑
//		for (int arr : arrlist){
//			System.out.print(arr+", ");
//		}
		//將一維路徑記錄轉回二維陣列
		for (int w : arrlist){
			maze[w/7][w%7] = 9;
		}
		//將路徑修改為"9"
		int count = 0;
		for (int [] row : maze){
			for (int column : row){
//				System.out.print(column+" ");
				count += 1;
				if (count == 7){
					System.out.println();
					count = 0;
				}
			}
		}
		//將數字陣列改為文字陣列
		String [][] Smaze = new String[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (maze[i][j] == 2){
					Smaze[i][j] = "■";
				}
				if (maze[i][j] == 0){
					Smaze[i][j] = "□";
				}
				if (maze[i][j] == 9){
					Smaze[i][j] = "◇";
				}

			}
		}
		int count2 = 0;
		for (String [] row : Smaze){
			for (String column : row){
				System.out.print(column);
				count2 += 1;
				if (count2 == 7){
					System.out.println();
					count2 = 0;
				}
			}
		}
		
		
	}
}

