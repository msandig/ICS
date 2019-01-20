package de.dhbw.ics.scheduler;

import de.dhbw.ics.database.dao.BusySeatDao;
import de.dhbw.ics.vo.BusySeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatLockingScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(SeatLockingScheduler.class);

    @Autowired
    private BusySeatDao busySeatDao;

    @Scheduled(initialDelay = 10000, fixedRate = 300000)
    public void clearLockedSeats() {
        LOG.info("Starting UnlockSeatScheduler!");
        List<BusySeat> bsUpdate = new ArrayList<>();
        if (this.busySeatDao != null) {
            List<BusySeat> busySeats = this.busySeatDao.getAll();
            if(busySeats == null)
                return;

            for (BusySeat bs : busySeats) {
                if (bs.isLocked() && !bs.isBusy()) {
                    if (BusySeat.compareLockTimestamp(bs) <=  0) {
                        bs.setLocked(false);
                        bs.setTimestamp(0);
                        bs.setSessionID("");
                        bsUpdate.add(bs);
                    }
                }
            }
            this.busySeatDao.persistBatch(bsUpdate);
        }
        LOG.info("Finished UnlockSeatScheduler with {} unlocks!", bsUpdate.size());
    }
}
