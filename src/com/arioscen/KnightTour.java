package com.arioscen;

//參考騎士巡邏，每次選擇都考慮下下一步的選擇數量是最少的，便會有很高的機率可以完成巡邏
//第一個方法，計算下下步的選擇數量
//第二個方法，以下下步的選擇數量最少的優先選擇，並移動下一步
//第三個main方法，實際執行方法二，並打印棋盤


import java.util.ArrayList;
public class KnightTour {
	//設定棋盤
	static int [][] chess = new int[8][8];
	//設定初始位置
	static int rowx = 0;
	static int columnx = 0;
	//騎士的移動方式
	static int [][] step = {
			{1,   2},
			{1,  -2},
			{-1,  2},
			{-1, -2},
			{2,   1},
			{2,  -1},
			{-2,  1},
			{-2, -1}			
	};
	//計算下下一步可選擇的方向數量，並回傳
	public static int move_count (int row, int column){
		//統計可選擇數為count
		int count = 0;
		for (int i = 0; i < 8; i ++){
			//移動範圍要在棋盤之內
			if (row+step[i][0] >= 0 && row+step[i][0] <=7 && column+step[i][1] >= 0 && column+step[i][1] <= 7){
				//要先確認list的輸入值在可選範圍內。不可在上一層就用||做判斷  		  //因為要計算下下一步，所以第二步位置不能等於第一步位置
				if (chess[row+step[i][0]][column+step[i][1]] == 0 && row+step[i][0] != row && column+step[i][1] != column){
					//每找到一個位置count+1
					count += 1;
				}
			}
		}
		return count;
		
	}
	//路徑選擇與進行下一步
	public static void move (int row, int column){
		//設定隨機的list，避免方向固定。與二維陣列step做對照，最多有八種選擇，每使用一個就用remove刪除一個
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		//當下下步的選擇數小於choose時，以較小的優先選擇。(四個角的選擇性是最少的，能走就要優先走過)
		//每次選擇可能路徑數小於9，所以預設choose為9。
		int choose = 9;
		while (list.size() != 0){
			//每次方向選擇的可能性取決於list的列表值的總數
			int r = (int)(Math.random()*list.size());
			int i = list.get(r);
			if (row+step[i][0] >= 0 && row+step[i][0] <=7 && column+step[i][1] >= 0 && column+step[i][1] <= 7){
				if (chess[row+step[i][0]][column+step[i][1]] == 0){
					//導入move_count方法，每個下一步都計算下下步的可能選擇數量
					if (move_count(row+step[i][0], column+step[i][1]) < choose){
						//讓符合篩選條件的結果改變實際位置
						rowx = row+step[i][0];
						columnx = column+step[i][1];
						list.remove(r);
						//比對到另一個更好的路徑選擇時，更新choose，(下下步選擇最少的優先)
						choose = move_count(row+step[i][0], column+step[i][1]);
					}
					else {
						//不管是否有篩選成功都要刪掉，避免選擇重複
						list.remove(r);
					}
				}
				else {
					list.remove(r);
				}
			}
			else {
				list.remove(r);
			}
		}
		
	}
	//實際運作，總共有63步要走
	public static void main(String[] args) {
		//設定第一步的位置是1
		chess[rowx][columnx] = 1;
		for (int i = 2; i < 65; i++){
			move (rowx, columnx);
			if (chess[rowx][columnx] == 0){
				//記錄每一步的順序
				chess[rowx][columnx] = i;
			}

		}
		//打印整個棋盤
		for (int row [] : chess){
			for (int column : row){
				if (column < 10){
					System.out.print(column+"  ");
				}
				else {
					System.out.print(column+" ");
				}
			}
			System.out.println();
		}

	}

}

