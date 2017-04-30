package com.arioscen;

import java.util.ArrayList;

public class EightQueen {
	public static int total = 0;
	//�إߦ�m���@���}�C�A�`�@64��A�N��ѽL�W64����m��row��column
	public static int [][] place = new int[64][2];
	public static ArrayList modifylist2(int i, ArrayList list){
		ArrayList<Integer> list2 = new ArrayList<Integer>(list.size());
		list2.addAll(list);
		//�R���ӦZ��������m
		list2.remove(i);
		//��list2�Ұ����ˬd�ʧ@�A�R��i�o�@�B�ӦZ���������|
		for (int j = 0; j < list2.size(); j++){
			int l2j = (int)list2.get(j);
			int li = (int)list.get(i);
			//�R���ӦZ�o�@�B�����u�������|
			if (place[l2j][0] == place[li][0] || place[l2j][1] == place[li][1]){
				list2.remove(j);
				j -= 1;
			}
			else {
				//�R���ӦZ�o�@�B���׽u�������|(�F�n��)
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
				//�R���ӦZ�o�@�B���׽u�������|(��n��)
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
				//�R���ӦZ�o�@�B���׽u�������|(��_��)
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
				//�R���ӦZ�o�@�B���׽u�������|(�F�_��)
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
		}//for����
		return list2;
	}//modifylist2����
	//�L�X�ѽL
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
	}//print����
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
	}//queen����
	
	public static void main(String[] args) {
		//��g��m�}�C
		int count = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				place[count][0] = i;
				place[count][1] = j;
					count += 1;
			}
		}
		//��g�����}�C
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 64; i++){
			list.add(i);
		}
		queen(list, 0);
		//�C�ب��k�|�]�����᪺��ܦӭ��ơA�ҥH�o�쪺total�n���H8������
		int number = 8;
		int eight = 1;
		for (int i = number; i>0; i--){
			eight *= i;
		}
		System.out.println(total/eight);
	}//main����
	
//	//���եΪ�main��k�A�T�{modifylist2���`�B�@
//	public static void main(String[] args) {
//		//��g��m�}�C
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
//		//�H�N��J�n�z�諸��m���X�A�o�̥H��J0����
//		ArrayList listx2 = modifylist2(0, listx);
//		print(listx2);//�L�X���G���ӬO0�ӦZ����m�������蹳���אּ0		
//	}//���ե�main����
	
}//class EightQueen����
