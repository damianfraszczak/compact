/**
 *
 */
package pl.edu.wat.wcy.cop.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.domain.scenario.CrisisEvent;
import pl.edu.wat.wcy.cop.services.repositories.impl.CrisisEventRepository;

@Service
// Provides crisis event operations.
public class CrisisEventService extends AbstractServiceImpl<CrisisEvent, CrisisEventRepository> {

}
