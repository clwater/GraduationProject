import tornado.web
import tornado.ioloop
import socket

ip = "0.0.0.0"

class ParentRequesyHandler(tornado.web.RequestHandler):
    def showInfo(self):
        if ip != self.request.remote_ip:
            print("source ip :" , self.request.remote_ip)

class Main(ParentRequesyHandler):
    def get(self, *args, **kwargs):
        ParentRequesyHandler.showInfo(self)
        self.write("hello")

class Handler_Diary(ParentRequesyHandler):
    def get(self):
        ParentRequesyHandler.showInfo(self)
        indexclass = self.get_argument("in" , None)

        self.write('666' + indexclass)

application = tornado.web.Application([
    (r"/" , Main ),
    (r"/diary" , Handler_Diary),

])

def runServer():
    port = 8100
    application.listen(port)

    localIP = socket.gethostbyname(socket.gethostname())
    print("run in %s:%s"%(localIP,port))
    tornado.ioloop.IOLoop.instance().start()

runServer()