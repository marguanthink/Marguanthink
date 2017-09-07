package net.juyun.www.jvyunlib.photolib;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.juyun.www.jvyunlib.R;
import net.juyun.www.jvyunlib.photolib.entity.Photo;
import net.juyun.www.jvyunlib.photolib.event.OnItemCheckListener;
import net.juyun.www.jvyunlib.photolib.fragment.ImagePagerFragment;
import net.juyun.www.jvyunlib.photolib.fragment.PhotoPickerFragment;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class PhotoPickerActivity extends AppCompatActivity {

  private PhotoPickerFragment pickerFragment;
  private ImagePagerFragment imagePagerFragment;

  public final static String EXTRA_MAX_COUNT     = "MAX_COUNT";
  public final static String EXTRA_SHOW_CAMERA   = "SHOW_CAMERA";
  public final static String EXTRA_SHOW_GIF      = "SHOW_GIF";
  public final static String KEY_SELECTED_PHOTOS = "SELECTED_PHOTOS";
  public final static String EXTRA_GRID_COLUMN   = "column";

  private MenuItem menuDoneItem;

  public final static int DEFAULT_MAX_COUNT = 9;
  public final static int DEFAULT_COLUMN_NUMBER = 3;

  private int maxCount = DEFAULT_MAX_COUNT;

  /** to prevent multiple calls to inflate menu */
  private boolean menuIsInflated = false;

  private boolean showGif = false;
  private int columnNumber = DEFAULT_COLUMN_NUMBER;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    boolean showCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, true);
    boolean showGif    = getIntent().getBooleanExtra(EXTRA_SHOW_GIF, false);
    setShowGif(showGif);

    setContentView(R.layout.picker_activity_photo_picker);

    Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);
    setTitle(R.string.picker_title);

    ActionBar actionBar = getSupportActionBar();

    assert actionBar != null;
    actionBar.setDisplayHomeAsUpEnabled(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      actionBar.setElevation(25);
    }

    maxCount = getIntent().getIntExtra(EXTRA_MAX_COUNT, DEFAULT_MAX_COUNT);
    columnNumber = getIntent().getIntExtra(EXTRA_GRID_COLUMN, DEFAULT_COLUMN_NUMBER);

    pickerFragment = PhotoPickerFragment.newInstance(showCamera, showGif, columnNumber, maxCount);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, pickerFragment)
        .commit();
    getSupportFragmentManager().executePendingTransactions();

    pickerFragment.getPhotoGridAdapter().setOnItemCheckListener(new OnItemCheckListener() {
      @Override
      public boolean OnItemCheck(int position, Photo photo, final boolean isCheck, int selectedItemCount) {

        int total = selectedItemCount + (isCheck ? -1 : 1);

        menuDoneItem.setEnabled(total > 0);

        if (maxCount <= 1) {
          List<Photo> photos = pickerFragment.getPhotoGridAdapter().getSelectedPhotos();
          if (!photos.contains(photo)) {
            photos.clear();
            pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
          }
          return true;
        }

        if (total > maxCount) {
          Toast.makeText(getActivity(), getString(R.string.picker_over_max_count_tips, maxCount),
              LENGTH_LONG).show();
          return false;
        }
        menuDoneItem.setTitle(getString(R.string.picker_done_with_count, total, maxCount));
        return true;
      }
    });

  }


  /**
   * Overriding this method allows us to run our exit animation first, then exiting
   * the activity when it complete.
   */
  @Override
  public void onBackPressed() {
    if (imagePagerFragment != null && imagePagerFragment.isVisible()) {
      imagePagerFragment.runExitAnimation(new Runnable() {
        public void run() {
          if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
          }
        }
      });
    } else {
      super.onBackPressed();
    }
  }


  public void addImagePagerFragment(ImagePagerFragment imagePagerFragment) {
    this.imagePagerFragment = imagePagerFragment;
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, this.imagePagerFragment)
        .addToBackStack(null)
        .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    if (!menuIsInflated) {
      getMenuInflater().inflate(R.menu.picker_menu_picker, menu);
      menuDoneItem = menu.findItem(R.id.done);
      menuDoneItem.setEnabled(false);
      menuIsInflated = true;
      return true;
    }
    return false;
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == event.KEYCODE_BACK) {
      Intent intent = new Intent();
      ArrayList<String> selectedPhotos = pickerFragment.getPhotoGridAdapter().getSelectedPhotoPaths();
      intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
      setResult(RESULT_OK, intent);
      finish();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      super.onBackPressed();
      return true;
    }

    if (item.getItemId() == R.id.done) {
      Intent intent = new Intent();
      ArrayList<String> selectedPhotos = pickerFragment.getPhotoGridAdapter().getSelectedPhotoPaths();
      intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
      setResult(RESULT_OK, intent);
      finish();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public PhotoPickerActivity getActivity() {
    return this;
  }

  public boolean isShowGif() {
    return showGif;
  }

  public void setShowGif(boolean showGif) {
    this.showGif = showGif;
  }
}
