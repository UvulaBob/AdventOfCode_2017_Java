public class Firewall {

    int range;
    int currentScanSpot = 1;
    private boolean forward = true;

    void sweepOneSpot() {
        if (forward) {
            if (currentScanSpot < range) {
                currentScanSpot++;
            } else {
                currentScanSpot--;
                forward = false;
            }
        } else {
            if (currentScanSpot > 1) {
                currentScanSpot--;
            } else {
                currentScanSpot++;
                forward = true;
            }
        }
    }

    public String toString() {
        return String.format("%s/%s", currentScanSpot, range);
    }

}
