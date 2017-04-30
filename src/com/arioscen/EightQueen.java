package com.arioscen;

import java.util.ArrayList;

public class EightQueen {
	public static int total = 0;
	//建立位置的一維陣列，總共64格，代表棋盤上64的位置的row跟column
	public static int [][] place = new int[64][2];
	public static ArrayList modifylist2(int i, ArrayList list){
		ArrayList<Integer> list2 = new ArrayList<Integer>(list.size());
		list2.addAll(list);
		//刪除皇后本身的位置
		list2.remove(i);
		//對list2所做的檢查動作，刪除i這一步皇后的攻擊路徑
		for (int j = 0; j < list2.size(); j++){
			int l2j = (int)list2.get(j);
			int li = (int)list.get(i);
			//刪除皇后這一步的直線攻擊路徑
			if (place[l2j][0] == place[li][0] || place[l2j][1] == place[li][1]){
				list2.remove(j);
				j -= 1;
			}
			else {
				//刪除皇后這一步的斜線攻擊路徑(東南方)
				int row_es = place[li][0];
				int column_es = place[li][1];
				while (row_es < 8 && column_es < 8){
					row_es += 1;
					column_es += 1;
					if (place[l2j][0] == row_es && place[l2j][1] == column_es){
						list2.remove(j);
						j -= 1;
						break;
					}
				}
				//刪除皇后這一步的斜線攻擊路徑(西南方)
				int row_ws = place[li][0];
				int column_ws = place[li][1];
				while (row_ws < 8 && column_ws >= 0){
					row_ws += 1;
					column_ws -= 1;
					if (place[l2j][0] == row_ws && place[l2j][1] == column_ws){
						list2.remove(j);
						j -= 1;
						break;
					}
				}
				//刪除皇后這一步的斜線攻擊路徑(西北方)
				int row_wn = place[li][0];
				int column_wn = place[li][1];
				while (row_wn >= 0 && column_wn >= 0){
					row_wn -= 1;
					column_wn -= 1;
					if (place[l2j][0] == row_wn && place[l2j][1] == column_wn){
						list2.remove(j);
						j -= 1;
						break;
					}
				}
				//刪除皇后這一步的斜線攻擊路徑(東北方)
				int row_en = place[li][0];
				int column_en = place[li][1];
				while (row_en >= 0 && column_en < 8){
					row_en -= 1;
					column_en += 1;
					if (place[l2j][0] == row_en && place[l2j][1] == column_en){
						list2.remove(j);
						j -= 1;
						break;
					}
				}
			}
		}//for結束
		return list2;
	}//modifylist2結束
	//印出棋盤
	public static void print(ArrayList list2){
		int k = 0;
		int l = list2.size();
		int c = 0;
		for (int w = 0; w < 64; w++){
			if (l > 0){
				if ((int)list2.get(k) == w){
					if (w<10){
						System.out.printf("0%d ",w);
						k += 1;
						l -= 1;
					}
					else{
						System.out.printf("%d ",w);
						k += 1;
						l -=1 ;
					}						
				}
				else {
					System.out.printf("%s ", "00");
				}	
			}
			else {
				System.out.printf("%s ", "00");
			}
			c++;
			if (c == 8){
				System.out.println();
				c = 0;
			}
		}
		System.out.println("==================================");
	}//print結束
	public static void queen(ArrayList list, int step){
		if (step == 8){
			total += 1;
		}
		else if (list.size() != 0){
			for (int i = 0; i < list.size(); i++){
				int step2 = step + 1;
				queen(modifylist2(i, list), step2);
			}			
		}
	}//queen結尾
	
	public static void main(String[] args) {
		//填寫位置陣列
		int count = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				place[count][0] = i;
				place[count][1] = j;
					count += 1;
			}
		}
		//填寫對應陣列
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 64; i++){
			list.add(i);
		}
		queen(list, 0);
		//每種走法會因為先後的選擇而重複，所以得到的total要除以8的階乘
		int number = 8;
		int eight = 1;
		for (int i = number; i>0; i--){
			eight *= i;
		}
		System.out.println(total/eight);
	}//main結尾
	
//	//測試用的main方法，確認modifylist2正常運作
//	public static void main(String[] args) {
//		//填寫位置陣列
//		int count = 0;
//		for (int i = 0; i < 8; i++){
//			for (int j = 0; j < 8; j++){
//				place[count][0] = i;
//				place[count][1] = j;
//					count += 1;
//			}
//		}
//	
//		ArrayList<Integer> listx = new ArrayList<Integer>();
//		for (int i = 0; i < 64; i++){
//			listx.add(i);
//		}
//		//隨意填入要篩選的位置號碼，這裡以輸入0為例
//		ArrayList listx2 = modifylist2(0, listx);
//		print(listx2);//印出結果應該是0皇后的位置的攻擊方像都改為0		
//	}//測試用main結尾
	
}//class EightQueen結尾
