
## Create a new angular project
ng new <project-name>

## Create the angular service:
frontend> ng generate service services/websocket

## Install SocketJs
frontend> npm install --save-dev @types/sockjs-client
 OR

 npm install sockjs-client

## Install STOMP 
frontend>  npm install  @stomp/stompjs

## 1. Open your angular.json file. 
## 2. Find the "build" section for your project. 
## 3.Add "allowedCommonJsDependencies": ["sockjs-client"] under architect -> build -> options.
{
  "projects": {
    "your-project-name": {
      "architect": {
        "build": {
          "options": {
            "allowedCommonJsDependencies": ["sockjs-client"]
          }
        }
      }
    }
  }
}

## install the global and process polyfill packages:
npm install global process --save

## Modify package.json:

 "browser": {
    "global": "global",
    "process": "process/browser"
  },

## add this to tsconfig.app.json
,
  "files": [
    "src/main.ts",
     "src/polyfills.ts"
  ],

## and more please follow the video...