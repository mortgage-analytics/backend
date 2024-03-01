package Group28.Backend.service;

import Group28.Backend.domain.Lead;
import Group28.Backend.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService
{
    @Autowired
    LeadRepository leadRepository;

    public List<Lead> getAll()
    {
        return leadRepository.findAll();
    }
}
