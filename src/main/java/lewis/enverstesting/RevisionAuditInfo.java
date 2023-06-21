package lewis.enverstesting;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RevisionAuditInfo {
    @Id
    private int rev;

    private long revtstmp;

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public long getRevtstmp() {
        return revtstmp;
    }

    public void setRevtstmp(long revtstmp) {
        this.revtstmp = revtstmp;
    }
}
