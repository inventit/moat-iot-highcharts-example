/*
 * LEGAL NOTICE
 *
 * Copyright (C) 2013 InventIt Inc. All rights reserved.
 *
 * This source code, product and/or document is protected under licenses 
 * restricting its use, copying, distribution, and decompilation.
 * No part of this source code, product or document may be reproduced in
 * any form by any means without prior written authorization of InventIt Inc.
 * and its licensors, if any.
 *
 * InventIt Inc.
 * 9F KOJIMACHI CP BUILDING
 * 4-4-7 Kojimachi, Chiyoda-ku, Tokyo 102-0083
 * JAPAN
 * http://www.yourinventit.com/
 */

#include <servicesync/moat.h>

sse_int
moat_register_model(Moat in_moat, sse_char *in_model_name, ModelMapper *in_mapper, sse_pointer in_model_context)
{
	return SSE_E_OK;
}
sse_int
moat_register_model_with_desc(Moat in_moat, sse_char *in_model_name, sse_char *in_desc, ModelMapper *in_mapper, sse_pointer in_model_context)
{
	return SSE_E_OK;
}
sse_int
moat_unregister_model(Moat in_moat, sse_char *in_model_name)
{
	return SSE_E_OK;
}
sse_int
moat_send_notification(Moat in_moat, sse_char *in_urn, sse_char *in_key, sse_char *in_model_name, MoatObject *collection, MoatNotifyResultProc in_proc, sse_pointer in_user_data)
{
	return SSE_E_OK;
}
void
moat_set_user_data(Moat in_moat, sse_pointer user_data)
{
}
sse_pointer
moat_get_user_data(Moat in_moat)
{
	return SSE_E_OK;
}

sse_int
moat_datastore_save_object(Moat in_moat, sse_char *in_key, MoatObject *in_obj)
{
	return SSE_E_OK;
}
sse_int
moat_datastore_load_object(Moat in_moat, sse_char *in_key, MoatObject **out_obj)
{
	return SSE_E_OK;
}
sse_int
moat_datastore_remove_object(Moat in_moat, sse_char *in_key)
{
	return SSE_E_OK;
}
sse_int
moat_datastore_save_value(Moat in_moat, sse_char *in_key, MoatValue *in_value)
{
	return SSE_E_OK;
}
sse_int
moat_datastore_load_value(Moat in_moat, sse_char *in_key, MoatValue **out_value)
{
	return SSE_E_OK;
}
sse_int
moat_datastore_remove_value(Moat in_moat, sse_char *in_key)
{
	return SSE_E_OK;
}

sse_int
moat_init(sse_char *in_urn, Moat *out_moat)
{
	return SSE_E_OK;
}
void
moat_destroy(Moat in_moat)
{
}
sse_int
moat_run(Moat in_moat)
{
	return SSE_E_OK;
}
void
moat_quit(Moat in_moat)
{
}

MoatValue *
moat_value_new(void)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_clone(MoatValue *self)
{
	return SSE_E_OK;
}
void
moat_value_free(MoatValue *self)
{
}
moat_value_type
moat_value_get_type(MoatValue *self)
{
	return SSE_E_OK;
}
sse_uint
moat_value_get_size(MoatValue *self)
{
	return SSE_E_OK;
}
sse_pointer
moat_value_peek_value(MoatValue *self)
{
	return SSE_E_OK;
}
sse_int
moat_value_set_value(MoatValue *self, moat_value_type in_type, sse_pointer in_v, sse_uint in_len, sse_bool in_dup)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_boolean(MoatValue *self, sse_bool *out_bool_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_int16(MoatValue *self, sse_int16 *out_int16_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_int32(MoatValue *self, sse_int32 *out_int32_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_int64(MoatValue *self, sse_int64 *out_int64_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_float(MoatValue *self, sse_float *out_float_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_double(MoatValue *self, sse_double *out_double_val)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_string(MoatValue *self, sse_char **out_str_val, sse_uint *out_len)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_binary(MoatValue *self, sse_byte **out_bin_val, sse_uint *out_size)
{
	return SSE_E_OK;
}
sse_int
moat_value_get_object(MoatValue *self, MoatObject **out_obj_val)
{
	return SSE_E_OK;
}
void
moat_value_set_boolean(MoatValue *self, sse_bool in_bool_val)
{
}
void
moat_value_set_int16(MoatValue *self, sse_int16 in_int16_val)
{
}
void
moat_value_set_int32(MoatValue *self, sse_int32 in_int32_val)
{
}
void
moat_value_set_int64(MoatValue *self, sse_int64 in_int64_val)
{
}
void
moat_value_set_float(MoatValue *self, sse_float in_float_val)
{
}
void
moat_value_set_double(MoatValue *self, sse_double in_double_val)
{
}
void
moat_value_set_null(MoatValue *self)
{
}
sse_int
moat_value_set_string(MoatValue *self, sse_char *in_str_val, sse_uint in_len, sse_bool in_dup)
{
	return SSE_E_OK;
}
sse_int
moat_value_set_binary(MoatValue *self, sse_byte *in_bin_val, sse_uint in_size, sse_bool in_dup)
{
	return SSE_E_OK;
}
sse_int
moat_value_set_object(MoatValue *self, MoatObject *in_obj_val, sse_bool in_dup)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_boolean(sse_bool in_bool_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_int16(sse_int16 in_int16_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_int32(sse_int32 in_int32_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_int64(sse_int64 in_int64_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_float(sse_float in_float_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_double(sse_double in_double_val)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_string(sse_char *in_str_val, sse_uint in_len, sse_bool in_dup)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_binary(sse_byte *in_bin_val, sse_uint in_size, sse_bool in_dup)
{
	return SSE_E_OK;
}
MoatValue *
moat_value_new_object(MoatObject *in_obj_val, sse_bool in_dup)
{
	return SSE_E_OK;
}

MoatObject *
moat_object_new(void)
{
	return SSE_E_OK;
}
void
moat_object_free(MoatObject *self)
{
}
MoatObject *
moat_object_clone(MoatObject *self)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_value(MoatObject *self, sse_char *in_key, MoatValue *in_value, sse_bool in_dup, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
void
moat_object_remove_value(MoatObject *self, sse_char *in_key)
{
}
void
moat_object_remove_all(MoatObject *self)
{
}
MoatValue *
moat_object_get_value(MoatObject *self, sse_char *in_key)
{
	return SSE_E_OK;
}
sse_uint
moat_object_get_length(MoatObject *self)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_boolean_value(MoatObject *self, sse_char *in_key, sse_bool in_bool_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_int16_value(MoatObject *self, sse_char *in_key, sse_int16 in_int16_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_int32_value(MoatObject *self, sse_char *in_key, sse_int32 in_int32_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_int64_value(MoatObject *self, sse_char *in_key, sse_int64 in_int64_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_float_value(MoatObject *self, sse_char *in_key, sse_float in_float_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_double_value(MoatObject *self, sse_char *in_key, sse_double in_double_val, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_string_value(MoatObject *self, sse_char *in_key, sse_char *in_str_val, sse_uint in_len, sse_bool in_dup, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_binary_value(MoatObject *self, sse_char *in_key, sse_byte *in_bin_val, sse_uint in_size, sse_bool in_dup, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_add_object_value(MoatObject *self, sse_char *in_key, MoatObject *in_obj_val, sse_bool in_dup, sse_bool in_overwrite)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_boolean_value(MoatObject *self, sse_char *in_key, sse_bool *out_bool_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_int16_value(MoatObject *self, sse_char *in_key, sse_int16 *out_int16_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_int32_value(MoatObject *self, sse_char *in_key, sse_int32 *out_int32_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_int64_value(MoatObject *self, sse_char *in_key, sse_int64 *out_int64_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_float_value(MoatObject *self, sse_char *in_key, sse_float *out_float_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_double_value(MoatObject *self, sse_char *in_key, sse_double *out_double_val)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_string_value(MoatObject *self, sse_char *in_key, sse_char **out_str_val, sse_uint *out_len)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_binary_value(MoatObject *self, sse_char *in_key, sse_byte **out_bin_val, sse_uint *out_size)
{
	return SSE_E_OK;
}
sse_int
moat_object_get_object_value(MoatObject *self, sse_char *in_key, MoatObject **out_obj_va)
{
	return SSE_E_OK;
}

MoatObjectIterator *
moat_object_create_iterator(MoatObject *self)
{
	return SSE_E_OK;
}
void
moat_object_iterator_free(MoatObjectIterator *self)
{
}
sse_bool
moat_object_iterator_has_next(MoatObjectIterator *self)
{
	return SSE_E_OK;
}
sse_char *
moat_object_iterator_get_next_key(MoatObjectIterator *self)
{
	return SSE_E_OK;
}
void
moat_object_iterator_reset(MoatObjectIterator *self)
{
}

SSESList *
sse_slist_new(void)
{
	return NULL;
}
void
sse_slist_free(SSESList *list)
{
}

SSESList *
sse_slist_add(SSESList *list, sse_pointer data)
{
	return NULL;
}
SSESList *
sse_slist_add_first(SSESList *list, sse_pointer data)
{
	return NULL;
}
SSESList *
sse_slist_concat(SSESList *list, SSESList *cat_list)
{
	return NULL;
}
SSESList *
sse_slist_remove(SSESList *list, sse_pointer data)
{
	return NULL;
}
SSESList *
sse_slist_remove_all(SSESList *list, sse_pointer data)
{
	return NULL;
}
SSESList *
sse_slist_unlink(SSESList *list, SSESList *link)
{
	return NULL;
}
SSESList *
sse_slist_find(SSESList *list, sse_pointer data)
{
	return NULL;
}
SSESList *
sse_slist_foreach(SSESList *list, sse_pointer data, SSECompareProc proc)
{
	return NULL;
}
SSESList *
sse_slist_get(SSESList *list, sse_int i)
{
	return NULL;
}
sse_pointer
sse_slist_get_data(SSESList *list, sse_int i)
{
	return NULL;
}
SSESList *
sse_slist_last(SSESList *list)
{
	return NULL;
}
sse_uint
sse_slist_length(SSESList *list)
{
	return 0;
}

SSEQueue *
sse_queue_new(void)
{
	return NULL;
}
void
sse_queue_free(SSEQueue *q)
{
}

void
sse_queue_clear(SSEQueue *q)
{
}
sse_int
sse_queue_enqueue(SSEQueue *q, sse_pointer data)
{
	return 0;
}
sse_pointer
sse_queue_dequeue(SSEQueue *q)
{
	return NULL;
}
sse_pointer
sse_queue_peek_next(SSEQueue *q)
{
	return NULL;
}
sse_bool
sse_queue_is_empty(SSEQueue *q)
{
	return sse_true;
}

sse_char *
sse_strcpy(sse_char *s1, const sse_char *s2)
{
	return NULL;
}
sse_char *
sse_strncpy(sse_char *s1, const sse_char *s2, sse_size n)
{
	return NULL;
}
sse_char *
sse_strcat(sse_char *s1, const sse_char *s2)
{
	return NULL;
}
sse_int
sse_strcmp(const sse_char *s1, const sse_char *s2)
{
	return 0;
}
sse_int
sse_strncmp(const sse_char *s1, const sse_char *s2, sse_size n)
{
	return 0;
}
sse_int
sse_strcasecmp(const sse_char *s1, const sse_char *s2)
{
	return 0;
}
sse_int
sse_strncasecmp(const sse_char *s1, const sse_char *s2, sse_size n)
{
	return 0;
}
sse_int
sse_strlen(const sse_char *s)
{
	return 0;
}
sse_char *
sse_strstr(const sse_char *s1, const sse_char *s2)
{
	return NULL;
}
sse_char *
sse_strchr(const sse_char *s, sse_int c)
{
	return NULL;
}
sse_char *
sse_strnchr(const sse_char *s, sse_size len, sse_int c)
{
	return NULL;
}
sse_char *
sse_strrchr(const sse_char *s, sse_int c)
{
	return NULL;
}
sse_char *
sse_strndup(const sse_char *s, sse_size n)
{
	return NULL;
}
sse_char *
sse_strdup(const sse_char *s)
{
	return NULL;
}

sse_char *
sse_u16tohex(sse_uint16 val, sse_char *buf)
{
	return NULL;
}

sse_byte
sse_hexntobyte(sse_char *s, sse_int n)
{
	return 0;
}
sse_byte
sse_hextobyte(sse_char *s)
{
	return 0;
}
sse_uint16
sse_hexntou16(sse_char *s, sse_int n)
{
	return 0;
}
sse_uint16
sse_hextou16(sse_char *s)
{
	return 0;
}
sse_uint32
sse_hexntou32(sse_char *s, sse_int n)
{
	return 0;
}
sse_uint32
sse_hextou32(sse_char *s)
{
	return 0;
}

void *
sse_memcpy(void *buf1, const void *buf2, sse_size size)
{
	return NULL;
}
void *
sse_memset(void *buf, sse_int32 ch, sse_size n)
{
	return NULL;
}
sse_int
sse_memcmp(void *buf1, void *buf2, sse_size size)
{
	return 0;
}
void *
sse_memdup(void *in_buff, sse_size in_size)
{
	return NULL;
}

sse_char
sse_to_lower_case(const sse_char in_c)
{
	return 0;
}
sse_char
sse_to_upper_case(const sse_char in_c)
{
	return 0;
}

sse_pointer
sse_malloc(sse_size size)
{
	return NULL;
}
void
sse_free(sse_pointer p)
{
}

sse_char *
sse_itoa(sse_int val, sse_char *buf)
{
	return NULL;
}

sse_int
sse_atoi(sse_char *s)
{
	return 0;
}
sse_int
sse_antoi(sse_char *s1, sse_int32 n)
{
	return 0;
}

sse_int32
sse_div(sse_int32 num, sse_int32 den)
{
	return 0;
}
sse_int16
sse_div16(sse_int16 num, sse_int16 den)
{
	return 0;
}
sse_int32
sse_div32(sse_int32 num, sse_int32 den)
{
	return 0;
}
sse_int64
sse_div64(sse_int64 num, sse_int64 den)
{
	return 0;
}
sse_int32
sse_mod(sse_int32 num, sse_int32 den)
{
	return 0;
}
sse_int16
sse_mod16(sse_int16 num, sse_int16 den)
{
	return 0;
}
sse_int32
sse_mod32(sse_int32 num, sse_int32 den)
{
	return 0;
}
sse_int64
sse_mod64(sse_int64 num, sse_int64 den)
{
	return 0;
}

