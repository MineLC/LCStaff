package lc.mine.staff.storage;

import java.util.UUID;

public final class StaffData {
    private boolean enableVanish = false;
    private UUID playerFreeze;

    public boolean getEnableVanish() {
        return this.enableVanish;
    }

    public void setVanish(boolean vanish) {
        this.enableVanish = vanish;
    }

    public UUID getPlayerFreeze() {
        return this.playerFreeze;
    }

    public void setPlayerFreeze(UUID player) {
        this.playerFreeze = player;
    }
}
