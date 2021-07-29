# GroupProject 24-05-21

**Qualifier System**

## Explain
```bash
 ~/groupproject-24-05-21/: tree -L 3 -I node_modules
.
├── README.md               # what you are reading ATM
├── backend                 # Spring boot application dir/folder
│   ├── databaseConfig
│   │   └── docker-compose.yml
    |                       # `docker-compose up`
│   └── qualifier
│       ├── HELP.md
│       ├── mvnw
│       ├── mvnw.cmd
│       ├── pom.xml
│       ├── src             # for devs
│       └── target
└── frontend                # React application dir/folder
    ├── README.md
    ├── package-lock.json
    ├── package.json
    ├── public
    │   ├── favicon.ico
    │   ├── index.html
    │   ├── manifest.json
    │   └── robots.txt
    ├── src                 # for devs
    │   ├── App.css
    │   ├── App.js
    │   ├── actions
    │   ├── components
    │   ├── index.css
    │   ├── index.js
    │   ├── pages
    │   ├── reducers
    │   └── utils
    └── yarn.lock
```
Both applications will have to start-up **separately**.


## Requirements
*backend*: you know what you doing.

*frontend*: [nodejs v12.0+](https://nodejs.org/en/download/) - like maven, nodejs is for package management
> [options](https://nodejs.org/en/download/package-manager/)


## Installation
```bash
git clone https://git.fdmgroup.com/william.wood/groupproject-24-05-21.git [someNameYouWant]
cd [someNameYouWant]
#import backend to IDE
cd frontend
npm install     # for first time install and subsequent package update
```


## Start
*backend*: one-click in IDE

*frontend*: 
```bash
cd frontend && npm start
```