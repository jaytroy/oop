package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;
import nl.rug.oop.rts.model.base.Node;
import nl.rug.oop.rts.model.entity.Army;
import nl.rug.oop.rts.model.entity.Faction;
import nl.rug.oop.rts.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Armies action handler.
 */
public class ArmyActions {
    private int nextArmyID = 0;

    /**
     * This function should handle adding an army to the selected node. It shows an input dialog that asks you to select
     * the faction of your army.
     *
     * @param graph     the graph in which we have the node
     * @param mainFrame the frame where this happens
     */
    public void addArmyToSelectedNode(Graph graph, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null) {
            Faction[] factions = Faction.values();
            Faction selectedFaction = (Faction) JOptionPane.showInputDialog(
                    mainFrame,
                    "Select a faction:",
                    "Add Army",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    factions,
                    factions[0]
            );
            if (selectedFaction != null) {
                Army army = new Army(nextArmyID, selectedFaction);
                selectedNode.addArmy(army);
                nextArmyID++;
                graph.notifyObservers();
            }
        }
    }

    /**
     * This function handles the removal of an army from the selected node.
     *
     * @param graph     the graph in which we have the node
     * @param mainFrame the frame where this happens
     */
    public void removeArmyFromSelectedNode(Graph graph, MainFrame mainFrame) {
        Node selectedNode = graph.getSelectedNode();
        if (selectedNode != null && selectedNode.isHasArmy()) {
            List<Army> armies = selectedNode.getArmies();
            List<String> armyOptions = new ArrayList<>();
            for (Army army : armies) {
                armyOptions.add(army.getFaction().toString() + " - Units: " + army.getUnits().size());
            }
            String[] armyOptionsArray = armyOptions.toArray(new String[0]);
            String selectedArmyString = (String) JOptionPane.showInputDialog(
                    mainFrame,
                    "Select an army to remove:",
                    "Remove Army",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    armyOptionsArray,
                    armyOptionsArray[0]
            );
            if (selectedArmyString != null) {
                String[] parts = selectedArmyString.split(" - Units: ");
                String factionString = parts[0];
                Faction faction = Faction.valueOf(factionString);
                int unitCount = Integer.parseInt(parts[1]);

                Army selectedArmy = null;
                for (Army army : armies) {
                    if (army.getFaction() == faction && army.getUnits().size() == unitCount) {
                        selectedArmy = army;
                        break;
                    }
                }
                if (selectedArmy != null) {
                    selectedNode.removeArmy(selectedArmy);
                    graph.notifyObservers();
                }
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No armies to remove from the selected node.");
        }
    }
}
