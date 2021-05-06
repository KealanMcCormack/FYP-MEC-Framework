package service.orchestrator.migration;

import service.orchestrator.nodes.ServiceNode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeferredMigrator implements Migrator {
    private final Map<ServiceNode, Integer> migrateCallCounts = new HashMap<>();

    @Override
    public void migrate(ServiceNode source, ServiceNode target) {
        migrateCallCounts.merge(target, 1, Integer::sum);
    }

    public List<ServiceNode> getTriggerableServiceNodes() {
        return migrateCallCounts.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
