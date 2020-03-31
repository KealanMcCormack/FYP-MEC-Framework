package service.edge;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

@CommandLine.Command(name = "cmMain", mixinStandardHelpOptions = true, version = "0.1")
public class cmMain implements Runnable{

//    @Option(names = { "-v", "--verbose" },
//            description = "Verbose mode. Helpful for troubleshooting.")
//    private boolean[] verbose = new boolean[0];

    @Option(names = { "-s", "--secure" },
            description = "Secure mode, only engages with orchestrators using SSL")
    private boolean secure;

    //@Option(names = { "-r", "--request" }, paramLabel = "requestedService", description = "requestedService")
    //File requestedService;

    @Parameters(index = "0", paramLabel = "address", description = "The address of the orchestrator, should be in format wss://{ip}:{port}")
    private URI address;

    //@Parameters(index = "1", paramLabel = "file", description = "The location of the service you wish to run")
    //private File file;

    @Override
    public void run() {
        if(!secure) {
            Edge edge = new Edge(address);
            edge.run();
        }else{
            try {
                new SSLMain(address);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        // By implementing Runnable or Callable, parsing, error handling and handling user
        // requests for usage help or version help can be done with one line of code.

        int exitCode = new CommandLine(new cmMain()).execute(args);
        System.exit(exitCode);
    }
}