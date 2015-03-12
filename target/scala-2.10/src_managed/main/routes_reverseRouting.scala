// @SOURCE:/home/crunchbang/documents/MobHCI/mhciback/conf/routes
// @HASH:fcbaca18ee19c96b697d437a39f72b3bce31247a
// @DATE:Thu Mar 12 19:26:08 GMT 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def addFriend(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addFriend")
}
                                                

// @LINE:8
def register(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                                                

// @LINE:11
def getPublicEvent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getPublicEvent")
}
                                                

// @LINE:7
def postgisVersion(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "postgis")
}
                                                

// @LINE:10
def nearbyPublicEvents(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "nearbyPublicEvents")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def addFriend : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addFriend",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addFriend"})
      }
   """
)
                        

// @LINE:8
def register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.register",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

// @LINE:11
def getPublicEvent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPublicEvent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getPublicEvent"})
      }
   """
)
                        

// @LINE:7
def postgisVersion : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.postgisVersion",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "postgis"})
      }
   """
)
                        

// @LINE:10
def nearbyPublicEvents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.nearbyPublicEvents",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "nearbyPublicEvents"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def addFriend(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addFriend(), HandlerDef(this, "controllers.Application", "addFriend", Seq(), "POST", """""", _prefix + """addFriend""")
)
                      

// @LINE:8
def register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.register(), HandlerDef(this, "controllers.Application", "register", Seq(), "POST", """""", _prefix + """register""")
)
                      

// @LINE:11
def getPublicEvent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPublicEvent(), HandlerDef(this, "controllers.Application", "getPublicEvent", Seq(), "POST", """""", _prefix + """getPublicEvent""")
)
                      

// @LINE:7
def postgisVersion(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.postgisVersion(), HandlerDef(this, "controllers.Application", "postgisVersion", Seq(), "GET", """""", _prefix + """postgis""")
)
                      

// @LINE:10
def nearbyPublicEvents(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.nearbyPublicEvents(), HandlerDef(this, "controllers.Application", "nearbyPublicEvents", Seq(), "POST", """""", _prefix + """nearbyPublicEvents""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    