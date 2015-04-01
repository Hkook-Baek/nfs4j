NFS4J
=====

The pure java implementation of NFS server version 3, 4.0 and 4.1 including pNFS extension.



Building from sources
---------------------

To build nfs4j from source code Java8 and Maven3 are required.


Starting a basic server
-------

The nfs4j comes with a basic stand-alone NFS server which is uses Spring-XML configuration file.

```$ java -jar basic-server/target/nfs4j-basic-server-<version>-jar-with-dependencies.jar oncrpcsvc.xml```

You can customize the configuration file to your needs. The **oncrpcsvc** bean is the one which will be started.

Implementing own NFS server
---------------------------

```java
public class App {

    public static void main(String[] args) throws Exception {

        // create an instance of a filesystem to be exported
        VirtualFileSystem vfs = new ....;

        // create the RPC service which will handle NFS requests
        OncRpcSvc nfsSvc = new OncRpcSvcBuilder()
                .withPort(2049)
                .withTCP()
                .withAutoPublish()
                .withWorkerThreadIoStrategy()
                .build();

        // specify file with export entries
        ExportFile exportFile = new ExportFile(....);

        // create NFS v4.1 server
        NFSServerV41 nfs4 = new NFSServerV41(
                new MDSOperationFactory(),
                new DeviceManager(),
                vfs,
                exportFile);

        // create NFS v3 and mountd servers
        NfsServerV3 nfs3 = new NfsServerV3(exportFile, vfs);
        MountServer mountd = new MountServer(exportFile, vfs);

        // register NFS servers at portmap service
        nfsSvc.register(new OncRpcProgram(100003, 4), nfs4);
        nfsSvc.register(new OncRpcProgram(100003, 3), nfs3);
        nfsSvc.register(new OncRpcProgram(100005, 3), mountd);

        // start RPC service
        nfsSvc.start();

        System.in.read();
    }
}
```

Use NFS4J in your project
-----------------------------

```xml
<dependency>
    <groupId>org.dcache</groupId>
    <artifactId>nfs4j-core</artifactId>
    <version>0.10.2</version>
</dependency>

<repositories>
    <repository>
        <id>dcache-releases</id>
        <name>dCache.ORG maven repository</name>
        <url>https://download.dcache.org/nexus/content/repositories/releases</url>
        <layout>default</layout>
    </repository>
</repositories>
```

License:
--------

licensed under [LGPLv2](http://www.gnu.org/licenses/lgpl-2.0.txt "LGPLv2") (or later)

Contact Us
---------
For help and development related discussions please contact us: *dev (@) dcache (.) org* 
