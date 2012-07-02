package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.*;

import ar.edu.itba.it.paw.domain.entities.*;

public interface AdRepository {
	public List<Ad> getRandomAds(int maxResults);
}
