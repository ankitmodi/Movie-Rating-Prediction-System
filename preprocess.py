#!/usr/bin/python



#@author Ankit Modi
 #This is not a comment.

from __future__ import print_function
f1 = open('u.data', 'r')
f2 = open('u1.data', 'w')


mylist = [];


	
def user_info(usr_id):
	f3 = open('u.user', 'r')
	for line in f3:
		temp = line.split('|')
		if(usr_id == temp[0]):
			temp[4] = temp[4][:-1]
			return temp



def movie_info(mov_id):
	f4 = open('u.item', 'r')
	for line in f4:
		temp = line.split('|')
		#print (temp)
		if(mov_id == temp[0]):
			temp[4] = temp[4][:-1]
			return temp


#print(movie_info('5'))
#print(len(movie_info('5')))


for line in f1:
	mylist = line.split('	')
	user_list = user_info(mylist[0])
	movie_list = movie_info(mylist[1])
	#print(temp_list)
	#print(mylist)
	#print(temp1)
	f2.write(mylist[0] + '	' + user_list[1] + '	' + user_list[2] +  '	' +  mylist[1] + '	' + movie_list[1] + '	' + movie_list[5] + '	' + movie_list[6] + '	' +movie_list[7] + '	' +movie_list[8] + '	' +movie_list[9] + '	' +movie_list[10] + '	' +movie_list[11] + '	' +movie_list[12] + '	' +movie_list[13] + '	' +movie_list[14] + '	' +movie_list[15] + '	' +movie_list[16] + '	' +movie_list[17] + '	' +movie_list[18] + '	' +movie_list[19] + '	' +movie_list[20] + '	' +movie_list[21] + '	' +movie_list[22] + '	' +movie_list[23]  + '	' + mylist[2] + '\n')
	#f2.write(mylist[0] + '	' + temp[1] + '	' + mylist[1] + '	' + mylist[2] + '\n')
