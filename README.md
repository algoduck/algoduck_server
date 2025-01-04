# AlgoDuck Spring Server

AlgoDuck Server

## Prerequisites

#### clone repo

```bash
$ git clone https://github.com/jwelyl/algoduck.git
```

#### Google Checkstyle 적용
```bash
$ git config --add checkstyle.jar hooks/checkstyle-10.17.0-all.jar
$ git config --add checkstyle.checkfile config/checkstyle/google_checks.xml 
```

#### Git Hooks 사용
```bash
$ git config --local core.hookspath hooks
```