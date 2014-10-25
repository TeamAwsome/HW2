from panda3d.core import CollisionTraverser,CollisionNode
from panda3d.core import CollisionHandlerQueue,CollisionRay
from panda3d.core import Vec3,Vec4,BitMask32
from direct.actor.Actor import Actor

class PlayerObject():
	def __init__(self, render, username, x, y, z, h):
		self.username = username
		self.isMoving = False
		self.render = render

		self.keyMap = {"left":0, "right":0, "forward":0, "cam-left":0, "cam-right":0}

		self.actor = Actor("models/ralph", {"run":"models/ralph-run", "walk":"models/ralph-walk"})
		self.actor.reparentTo(render)
		self.actor.setScale(0.2)
		self.actor.setPos(x, y, z)
		self.actor.setH(h)

		self.cTrav = CollisionTraverser()
		self.GroundRay = CollisionRay()
		self.GroundRay.setOrigin(0,0,1000)
		self.GroundRay.setDirection(0,0,-1)
		self.GroundCol = CollisionNode('actorRay')
		self.GroundCol.addSolid(self.GroundRay)
		self.GroundCol.setFromCollideMask(BitMask32.bit(0))
		self.GroundCol.setIntoCollideMask(BitMask32.allOff())
		self.GroundColNp = self.actor.attachNewNode(self.GroundCol)
		self.GroundHandler = CollisionHandlerQueue()
		self.cTrav.addCollider(self.GroundColNp, self.GroundHandler)

	"""
	@Marvin
	Move moves the player around the map based on 
	Must be called from a task to function properly
	Currently set up to take in random test data
	Non-random will have keys supplied from client input
	"""
	def move(self, key):
		actor = self.actor

		startpos = actor.getPos()

		if (key==0):
			actor.setH(actor.getH() + 300 * globalClock.getDt())
		if (key==1):
			actor.setH(actor.getH() - 300 * globalClock.getDt())
		if (key==2):
			actor.setY(actor, -25 * globalClock.getDt())

		if (key==0) or (key==1) or (key==2):
			if self.isMoving is False:
				actor.loop("run")
				self.isMoving = True
		else:
			if self.isMoving:
				actor.stop()
				actor.pose("walk",5)
				self.isMoving = False

		self.cTrav.traverse(render)

		entries = []
		for i in range(self.GroundHandler.getNumEntries()):
			entry = self.GroundHandler.getEntry(i)
			entries.append(entry)
		entries.sort(lambda x,y: cmp(y.getSurfacePoint(render).getZ(), x.getSurfacePoint(render).getZ()))
		if (len(entries)>0) and (entries[0].getIntoNode().getName() == "terrain"):
			self.actor.setZ(entries[0].getSurfacePoint(render).getZ())
		else:
			self.actor.setPos(startpos)

		"""
		if (self.keyMap["left"]!=0):
			self.actor.setH(self.actor.getH() + 300 * globalClock.getDt())
		if (self.keyMap["right"]!=0):
			self.actor.setH(self.actor.getH() - 300 * globalClock.getDt())
		if (self.keyMap["forward"]!=0):
			self.actor.setY(self.actor, -25 * globalClock.getDt())

        if (self.keyMap["forward"]!=0) or (self.keyMap["left"]!=0) or (self.keyMap["right"]!=0):
        	if self.isMoving is False:
        		self.actor.loop("run")
        		self.isMoving = True
        else:
        	if self.isMoving:
        		self.actor.stop()
        		self.actor.pose("walk",5)
        		self.isMoving = False

        self.cTrav.traverse(render)

		entries = []
		for i in range(self.GroundHandler.getNumEntries()):
			entry = self.GroundHandler.getEntry(i)
			entries.append(entry)
		entries.sort(lambda x,y: cmp(y.getSurfacePoint(render).getZ(), x.getSurfacePoint(render).getZ()))
		if (len(entries)>0) and (entries[0].getIntoNode().getName() == "terrain"):
			self.actor.setZ(entries[0].getSurfacePoint(render).getZ())
		else:
			self.actor.setPos(startpos)
		"""