package pl.forex.trading_platform.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.forex.trading_platform.domain.settings.PlatformSettings;
import pl.forex.trading_platform.repository.PlatformSettingsRepository;

@Service
@Getter
@Setter
@Transactional
public class LoadPlatformSettingsImpl implements LoadPlatformSettings {

    @Autowired
    private PlatformSettingsRepository platformSettingsRepository;

    @Override
    public int loadDecisionTime() {
    return platformSettingsRepository.getOne(1L).getDecisionTime();
    }

    @Override
    public PlatformSettings loadAllSettings() {
        return platformSettingsRepository.getOne(1L);
    }
}
