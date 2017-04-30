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
		//�p��maze���h�֭Ӹ��
		//maze.length���maze���X��row
		int maze_length = 0;
		for (int i = 0; i < maze.length; i++){
			maze_length += maze[i].length;
		}
		//�N�G���}�Cmaze�ন�@���}�C
		int maze_count = 0;
		int [] maze_c = new int[maze_length];
		for (int [] row : maze){
			for (int column: row){
			maze_c[maze_count] = column;
			maze_count += 1;
			}
		}
		//�{�b��m
		int place = 7;
		//���ʡA�W:-7; �U:+7; ��:-1; �k:+1
		//����:0<=place<=48
		int top = maze[0].length-1;
		int last = maze[maze.length-1].length;
		int low = maze_length-last;
		//�X�f������:1.�Ӧ�m���ȵ���0  2.�b�g�c���
		//place <= top || place >= low || (place-6)%7 == 0 || place%7 == 0
		//����:�p�G�g�c�Ϊ����W�h?
		
		//���٦���L�����I�x�ssavepoint
		//�s�@�C���I�ˬd��A��ܤW�U���k�O�_�i�H���A0�i; 2���i
		int [][] savepoint = new int[maze_length][4];
		//�s�@���|�O���}�C
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		//�O���Ĥ@�B
		arrlist.add(7);
		//��arrlist�b�j�餺�ϥΤW���ݭn�A�h�W�[�F�@�ӭȡA�����^�餧��R��
		arrlist.add(7);	
		//�}�l���g�c
		while (true){
			if (place != 7 && (place <= top || place >= low || (place-6)%7==0 || place%7 == 0)){
				System.out.println("end");
				break;
			}
			//�ˬd�|�P�i������
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
			//����W�@���������A�קK���^�Y��
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
			//�}�l���ӥi����V�e�i�A�C���e�i�O�����|
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
				//�L���i���A�^��W�@�B�A�ç⤣�q��������A�R�����|�O��
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
			//�ˬd�{�b��m
//			System.out.println(place+",");			
		}
		//�R���ϥΤW�h�C�X���Ĥ@��
		arrlist.remove(0);
		//���L�@�����|
//		for (int arr : arrlist){
//			System.out.print(arr+", ");
//		}
		//�N�@�����|�O����^�G���}�C
		for (int w : arrlist){
			maze[w/7][w%7] = 9;
		}
		//�N���|�קאּ"9"
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
		//�N�Ʀr�}�C�אּ��r�}�C
		String [][] Smaze = new String[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (maze[i][j] == 2){
					Smaze[i][j] = "��";
				}
				if (maze[i][j] == 0){
					Smaze[i][j] = "��";
				}
				if (maze[i][j] == 9){
					Smaze[i][j] = "��";
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

