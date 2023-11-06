![MAQS Logo](https://github.com/OpenMAQS/openmaqs-java/blob/main/docs/resources/maqsfull.png?raw=true)

| Check | Status                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|-------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|**Pipeline**| [![OpenMAQS Java Pipeline](https://github.com/OpenMAQS/openmaqs-java/actions/workflows/maven.yml/badge.svg)](https://github.com/OpenMAQS/openmaqs-java/actions/workflows/maven.yml)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|**Code Quality**| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=OpenMAQS_openmaqs-java&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=OpenMAQS_openmaqs-java) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=OpenMAQS_openmaqs-java&metric=coverage)](https://sonarcloud.io/dashboard?id=OpenMAQS_openmaqs-java) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=OpenMAQS_openmaqs-java&metric=bugs)](https://sonarcloud.io/dashboard?id=OpenMAQS_openmaqs-java) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=OpenMAQS_openmaqs-java&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=OpenMAQS_openmaqs-dotnet) |
|**License**| [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://raw.githubusercontent.com/OpenMAQS/maqs-java/main/LICENSE)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|**Latest Release**| [![Maven Central](https://img.shields.io/maven-central/v/io.github.openmaqs/openmaqs-java?style=plastic)](https://central.sonatype.com/artifact/io.github.openmaqs/openmaqs-java/overview)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

## Powered By
[![Testing Powered By SauceLabs](https://opensource.saucelabs.com/images/opensauce/powered-by-saucelabs-badge-white.png?sanitize=true "Testing Powered By SauceLabs")](https://saucelabs.com)  
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=OpenMAQS_openmaqs-dotnet)

## Introduction 
MAQS stands for Modular Automation Quick Start (Java Edition).

It …
 - is a modular test automation framework
 - can be used as the base for your automation project or individual pieces can be used to enhance existing frameworks
 - is maintained/extended by volunteers

The main idea behind MAQS is to avoid **reinventing the wheel**. 
Most automation engagements have you doing the same basic steps to get a functioning framework implemented. 
Utilizing project templates, Maven, and utility libraries we are able to have a functioning framework up and running in minutes, almost entirely removing on the initial time investment on implementating an automation solution.

## Where can I get the Latest release?

You can pull it from the Maven Central repositories:
```xml
<dependency>
    <groupId>io.github.openmaqs</groupId>
    <artifactId>openmaqs-java</artifactId>
    <version>3.0.1</version>
</dependency>
```

And with gradle:
```properties
implementation group: 'io.github.openmaqs', name: 'openmaqs-java', version: '3.0.0'
```


## Documentation
[MAQS docs](https://openmaqs.github.io/openmaqs-java/#/)

## License
The MIT License (MIT)
Copyright (c) 2023 OpenMAQS

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
