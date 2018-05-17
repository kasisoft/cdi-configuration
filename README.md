# DISCONTINUED
# DISCONTINUED
# DISCONTINUED

# Purpose


This library provides a production factory for settings of various types.


# Infos

* [eMail: daniel.kasmeroglu@kasisoft.net](mailto:daniel.kasmeroglu@kasisoft.net)
* [GIT](https://kasisoft.com/bitbucket/projects/GRAV/repos/configuration)


# Development Setup

I assume that you're familiar with Maven. If not I suggest to visit the following page:

* https://maven.apache.org/


## Requirements

* Java 8


## Maven

### Releases

     <dependency>
         <groupId>com.kasisoft.cdi</groupId>
        <artifactId>configuration</artifactId>
        <version>0.8</version>
     </dependency>


### Snapshots

Snapshots can be used while accessing a dedicated maven repository. Your POM needs the following settings:

     <dependency>
       <groupId>com.kasisoft.cdi</groupId>
       <artifactId>configuration</artifactId>
       <version>0.9-SNAPSHOT</version>
     </dependency>
     
     <repositories>
         <repository>
             <id>libs-kasisoft</id>
             <url>https://kasisoft.com/artifactory/libs-kasisoft</url>
             <releases>
                 <enabled>true</enabled>
             </releases>
             <snapshots>
                 <enabled>true</enabled>
             </snapshots>
         </repository>
     </repositories>
     
     
# USAGE

By default all properties within the resources /settings.properties will be loaded through __SettingsLoader__.

    @Inject @DirSetting(value = "myproperty")
    private File      value;

or:

    @Inject @DirSetting(value = "myproperty")
    private Path      value;


# License

MIT License

Copyright (c) 2017 Daniel Kasmeroglu (Kasisoft)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
