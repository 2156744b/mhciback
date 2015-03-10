// @SOURCE:/home/crunchbang/documents/MobHCI/mhciback/conf/routes
// @HASH:26132831f3363b33dd9b81f84a1b4b5c0240d730
// @DATE:Tue Mar 10 14:42:42 GMT 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def postgis_version(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "postgis")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:8
def register(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                                                
    
}
                          
}
                  


// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def postgis_version : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.postgis_version",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "postgis"})
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
                        

// @LINE:8
def register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.register",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def postgis_version(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.postgis_version(), HandlerDef(this, "controllers.Application", "postgis_version", Seq(), "GET", """""", _prefix + """postgis""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:8
def register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.register(), HandlerDef(this, "controllers.Application", "register", Seq(), "POST", """""", _prefix + """register""")
)
                      
    
}
                          
}
        
    