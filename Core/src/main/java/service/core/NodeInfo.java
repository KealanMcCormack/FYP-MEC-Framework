package service.core;

import org.java_websocket.WebSocket;

import java.net.InetAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// todo remove unnecessary fields
public class NodeInfo extends Message {
    private UUID uuid;
    private WebSocket webSocket;
    private boolean serviceRunning;
    private List<Double> cpuLoad = Collections.emptyList();
    private List<Double> memoryLoad = Collections.emptyList();
    private List<Long> mainMemory = Collections.emptyList();
    private List<Long> storage = Collections.emptyList();
    private Map<UUID, List<Long>> latencies = Collections.emptyMap();
    private boolean trustworthy = true;
    private URI serviceHostAddress;
    private InetAddress globalIpAddress;

    public NodeInfo() {
        super(Message.MessageTypes.NODE_INFO);
    }

    public NodeInfo(UUID uuid, boolean serviceRunning, URI serviceUri) {
        this();
        this.uuid = uuid;
        this.serviceRunning = serviceRunning;
        this.serviceHostAddress = serviceUri;
    }

    public Map<UUID, List<Long>> getLatencies() {
        return latencies;
    }

    public void setLatencies(Map<UUID, List<Long>> latencies) {
        this.latencies = latencies;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public boolean isServiceRunning() {
        return serviceRunning;
    }

    public void setServiceRunning(boolean serviceName) {
        this.serviceRunning = serviceName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isTrustworthy() {
        return trustworthy;
    }

    public void setTrustworthy(boolean trustworthy) {
        this.trustworthy = trustworthy;
    }

    public URI getServiceHostAddress() {
        return serviceHostAddress;
    }

    public void setServiceHostAddress(URI serviceHostAddress) {
        this.serviceHostAddress = serviceHostAddress;
    }

    public List<Double> getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(List<Double> cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public List<Double> getMemoryLoad() {
        return memoryLoad;
    }

    public void setMemoryLoad(List<Double> memoryLoad) {
        this.memoryLoad = memoryLoad;
    }

    public List<Long> getMainMemory() {
        return mainMemory;
    }

    public void setMainMemory(List<Long> mainMemory) {
        this.mainMemory = mainMemory;
    }

    public List<Long> getStorage() {
        return storage;
    }

    public void setStorage(List<Long> storage) {
        this.storage = storage;
    }

    public InetAddress getGlobalIpAddress() {
        return globalIpAddress;
    }

    public void setGlobalIpAddress(InetAddress globalIpAddress) {
        this.globalIpAddress = globalIpAddress;
    }

    private static String mapToString(Map<?, ?> map) {
        return map.entrySet().stream()
                .map(e -> new StringBuilder().append(e.getKey()).append(": ").append(e.getValue()).append('\n'))
                .reduce(new StringBuilder(), StringBuilder::append)
                .toString();
    }

    @Override
    public String toString() {
        return String.format(" type=%s UUID=%s remoteSA=%s servicePort=%d serviceRunning=%b cpuLoad=%s memoryLoad=%s" +
                        "mainMemory=%s storage=%s latencies=%s",
                getType(), uuid, webSocket.getRemoteSocketAddress(), serviceHostAddress.getPort(), serviceRunning,
                cpuLoad, memoryLoad, mainMemory, storage, mapToString(latencies)
        );
    }
}
