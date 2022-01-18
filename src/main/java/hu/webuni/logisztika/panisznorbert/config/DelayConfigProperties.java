package hu.webuni.logisztika.panisznorbert.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistic")
@Component
public class DelayConfigProperties {

    private Delay delay = new Delay();

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public static class Delay{

        private Integer delay_30_minutes;
        private Integer delay_60_minutes;
        private Integer delay_120_minutes;

        public Integer getDelay_30_minutes() {
            return delay_30_minutes;
        }

        public void setDelay_30_minutes(Integer delay_30_minutes) {
            this.delay_30_minutes = delay_30_minutes;
        }

        public Integer getDelay_60_minutes() {
            return delay_60_minutes;
        }

        public void setDelay_60_minutes(Integer delay_60_minutes) {
            this.delay_60_minutes = delay_60_minutes;
        }

        public Integer getDelay_120_minutes() {
            return delay_120_minutes;
        }

        public void setDelay_120_minutes(Integer delay_120_minutes) {
            this.delay_120_minutes = delay_120_minutes;
        }
    }
}
