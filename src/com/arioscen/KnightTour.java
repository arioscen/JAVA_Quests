package com.arioscen;

//�Ѧ��M�h���ޡA�C����ܳ��Ҽ{�U�U�@�B����ܼƶq�O�̤֪��A�K�|���ܰ������v�i�H��������
//�Ĥ@�Ӥ�k�A�p��U�U�B����ܼƶq
//�ĤG�Ӥ�k�A�H�U�U�B����ܼƶq�̤֪��u����ܡA�ò��ʤU�@�B
//�ĤT��main��k�A��ڰ����k�G�A�å��L�ѽL


import java.util.ArrayList;
public class KnightTour {
	//�]�w�ѽL
	static int [][] chess = new int[8][8];
	//�]�w��l��m
	static int rowx = 0;
	static int columnx = 0;
	//�M�h�����ʤ覡
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
	//�p��U�U�@�B�i��ܪ���V�ƶq�A�æ^��
	public static int move_count (int row, int column){
		//�έp�i��ܼƬ�count
		int count = 0;
		for (int i = 0; i < 8; i ++){
			//���ʽd��n�b�ѽL����
			if (row+step[i][0] >= 0 && row+step[i][0] <=7 && column+step[i][1] >= 0 && column+step[i][1] <= 7){
				//�n���T�{list����J�Ȧb�i��d�򤺡C���i�b�W�@�h�N��||���P�_  		  //�]���n�p��U�U�@�B�A�ҥH�ĤG�B��m���൥��Ĥ@�B��m
				if (chess[row+step[i][0]][column+step[i][1]] == 0 && row+step[i][0] != row && column+step[i][1] != column){
					//�C���@�Ӧ�mcount+1
					count += 1;
				}
			}
		}
		return count;
		
	}
	//���|��ܻP�i��U�@�B
	public static void move (int row, int column){
		//�]�w�H����list�A�קK��V�T�w�C�P�G���}�Cstep����ӡA�̦h���K�ؿ�ܡA�C�ϥΤ@�ӴN��remove�R���@��
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		//��U�U�B����ܼƤp��choose�ɡA�H���p���u����ܡC(�|�Ө�����ܩʬO�̤֪��A�ਫ�N�n�u�����L)
		//�C����ܥi����|�Ƥp��9�A�ҥH�w�]choose��9�C
		int choose = 9;
		while (list.size() != 0){
			//�C����V��ܪ��i��ʨ��M��list���C��Ȫ��`��
			int r = (int)(Math.random()*list.size());
			int i = list.get(r);
			if (row+step[i][0] >= 0 && row+step[i][0] <=7 && column+step[i][1] >= 0 && column+step[i][1] <= 7){
				if (chess[row+step[i][0]][column+step[i][1]] == 0){
					//�ɤJmove_count��k�A�C�ӤU�@�B���p��U�U�B���i���ܼƶq
					if (move_count(row+step[i][0], column+step[i][1]) < choose){
						//���ŦX�z����󪺵��G���ܹ�ڦ�m
						rowx = row+step[i][0];
						columnx = column+step[i][1];
						list.remove(r);
						//����t�@�ӧ�n�����|��ܮɡA��schoose�A(�U�U�B��̤֪ܳ��u��)
						choose = move_count(row+step[i][0], column+step[i][1]);
					}
					else {
						//���ެO�_���z�令�\���n�R���A�קK��ܭ���
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
	//��ڹB�@�A�`�@��63�B�n��
	public static void main(String[] args) {
		//�]�w�Ĥ@�B����m�O1
		chess[rowx][columnx] = 1;
		for (int i = 2; i < 65; i++){
			move (rowx, columnx);
			if (chess[rowx][columnx] == 0){
				//�O���C�@�B������
				chess[rowx][columnx] = i;
			}

		}
		//���L��ӴѽL
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

