# evs-compare

Deploy to Tomcat 

 

We are using NPM with Vue.js version 3. 

 

Need a rewrite.config file. 

Place in tomcat/conf/Catalina/localhost/rewrite.config 

 

This is the rewrite.config file 

#------------------------------------------------------------------------------- 

# site that has example of this. 

# https://stackoverflow.com/questions/34619751/tomcat-8-url-rewrite-issues 

#------------------------------------------------------------------------------- 

#------------------------------------------------------------------------------- 

# Any subsequent file that the index.html file needs, needs to pass through. 

# This would be the js and css files along with other miscellaneous ones. 

#------------------------------------------------------------------------------- 

RewriteCond %{REQUEST_URI} .*\.(css|map|js|html|png|jpg|jpeg|gif|txt|ttf|json|woff|ico)$ [OR] 

  

#---------------------------------------- 

# Allow these service calls to go through 

#---------------------------------------- 

RewriteCond %{REQUEST_URI} ^(/evscompare/util).*$ [OR] 

RewriteCond %{REQUEST_URI} ^(/evscompare/download).*$ [OR] 

RewriteCond %{REQUEST_URI} ^(/evscompare/search).*$ [OR] 

  

RewriteRule ^(.*)$ - [L] 

RewriteRule ^(.*)$ /evscompare/index.html 

#------------------------------------------------------------------------------- 

 

 

Tomcat/conf/server.xml 

Add a rewrite valve 

 

Under the hostname... 

------------------------------ 

<Host name="localhost"  appBase="webapps" 

            unpackWARs="true" autoDeploy="true"> 

 //ADD THIS LINE 

        <Valve className="org.apache.catalina.valves.rewrite.RewriteValve" /> 

 

 

Vue.config.js  

 

// NODE_ENV is pulled in from .env.development or .env.production file 

// based on if the build mode (production/development).  The build command 

// is specified in package.json. 

module.exports = { 

publicPath: process.env.NODE_ENV === 'production' 

  ? '/evscompare/' 

  : '/' 

} 

 

VUE Router 

Add default path 

 

{ 

    path: '/:pathMatch(.*)*', 

    redirect: "/index.html", 

  }, 

 

.env.development/production 

 

NODE_ENV=production 

VUE_APP_BASE_URL=http://localhost:8889 

VUE_APP_ROOT_CONTEXT=/evscompare/       // <---- have a base directory line up with the base above 
