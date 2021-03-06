package com.alorma.github.presenter.repos;

import com.alorma.github.presenter.CacheWrapper;
import com.alorma.github.sdk.core.datasource.CacheDataSource;
import com.alorma.github.sdk.core.datasource.SdkItem;
import com.alorma.github.sdk.core.repositories.Repo;
import java.util.List;
import rx.Observable;

public class UserReposCache implements CacheDataSource<String, List<Repo>> {

  private String key;

  public UserReposCache(String key) {
    this.key = key + "_%s_page_%d";
  }

  @Override
  public void saveData(SdkItem<String> request, SdkItem<List<Repo>> sdkItem) {
    String cacheKey = String.format(key, request.getK(), request.getPage());
    CacheWrapper.reposCache().set(cacheKey, sdkItem);
  }

  @Override
  public Observable<SdkItem<List<Repo>>> getData(SdkItem<String> request) {
    String cacheKey = String.format(key, request.getK(), request.getPage());
    SdkItem<List<Repo>> sdkItem = CacheWrapper.reposCache().get(cacheKey);
    if (sdkItem == null || sdkItem.getK() == null || sdkItem.getK().isEmpty()) {
      return Observable.empty();
    } else {
      return Observable.just(sdkItem);
    }
  }
}
