package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;

public interface StrategieAttaque {
    void attaquer(Sid sid, int x, int y);
}
