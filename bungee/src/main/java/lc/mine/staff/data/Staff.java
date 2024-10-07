package lc.mine.staff.data;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Staff {
    private final ProxiedPlayer staff;
    private boolean staffChat = false;

    public Staff(ProxiedPlayer staff) {
        this.staff = staff;
    }

    public void setStaffChat(boolean staffChat) {
        this.staffChat = staffChat;
    }
    public ProxiedPlayer getStaff() {
        return staff;
    }
    public boolean isStaffChat() {
        return staffChat;
    }

    @Override
    public int hashCode() {
        return staff.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return (obj == this) || (obj instanceof Staff
            ? ((Staff)obj).staff.equals(this.staff)
            : false);
    }
}
