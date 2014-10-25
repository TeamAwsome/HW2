'''
Created on Oct 23, 2014

@author: Akshay
'''

class Player(object):


    def __init__(self, x1 = 0.0,y1=0.0,z1=0.0,h1=0.0):
        self.x = x1
        self.y = y1
        self.z = z1
        self.h = h1
    
    def __str__(self):
        msg = str(self.x)+","+str(self.y)+","+str(self.z)+","+str(self.z)
        return msg
        
    
    
    
        